package com.ericsson.jigsaw.simulator.mail.web.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.ericsson.jigsaw.simulator.mail.web.model.WebMail;

/**
 * Parse the mime message
 * 
 * Note: Don't support Content-Location header now.
 * 
 * @author exialia
 * 
 */
public class MimeMailUtil {

	/**
	 * 
	 * @param message
	 * @return The WebMail instance from cache or newly parsed.
	 * @throws IOException
	 * @throws MessagingException
	 */
	public static WebMail parse(Message message) throws IOException,
			MessagingException {

		// TODO if msg id is globally unique
		WebMail mail = SimpleWebmailCache.getInstance().getMailByMsgId(
				((MimeMessage) message).getMessageID());
		if (mail != null) {
			return mail;
		}

		WebMail result = new WebMail();
		result.setMessage(message); // copy a new one, retrieve the whole mail
									// from mail server

		if (isPlainText(message.getContentType())
				|| isHtml(message.getContentType())) {
			result.setDisplayBody(message);
		} else if (isMultipart(message.getContentType())) {
			MimeMessage mmessage = (MimeMessage) message;
			handleMultiPart(result, (Multipart) mmessage.getContent());
		} else {
			throw new MessagingException(
					"Message is neither plain text, nor HTML or multipart: "
							+ message.getContentType());
		}

		SimpleWebmailCache.getInstance().addMailToCache(
				((MimeMessage) message).getMessageID(), result);
		return result;
	}

	public static void replaceContentIdOrContentLocationInHtmlBody(
			WebMail mail, ContentInfoMapper mapper) throws MessagingException {
		Part displayBody = mail.getDisplayBody();
		if (!displayBody.isMimeType("text/html")) {
			return;
		}

		String htmlContent;
		try {
			htmlContent = (String) displayBody.getContent();
		} catch (IOException ioe) {
			throw new MessagingException(
					"Can not read HTML mail when trying to replace content ID.",
					ioe);
		}

		for (MimeBodyPart part : mail.getEmbedMedias()) {
			String contentId = part.getContentID().replace("<", "")
					.replace(">", "");
			String mappedResult = mapper.getContentIdMapper(contentId, mail);
			htmlContent = htmlContent.replaceAll("cid:" + contentId,
					mappedResult);
			// FIXME implement content-location header replacing
		}

		// setContent will remove the "Content-Type" header, so we save the
		// value and restore back
		String contentTypeHeader = displayBody.getHeader("Content-Type")[0];
		displayBody.setContent(htmlContent, "text/html");
		displayBody.setHeader("Content-Type", contentTypeHeader);
	
		mail.setDisplayBody(displayBody);
	}

	/**
	 * Print the whole Message's raw text content to an output steam
	 * 
	 * @param outputStream
	 */
	public static void dumpRawMsgToStream(Part mail, OutputStream outputStream)
			throws MessagingException {
		try {
			mail.writeTo(outputStream);
		} catch (IOException e) {
			throw new MessagingException("Can not dump the mail source.", e);
		}
	}

	public static String dumpRawMsg(Part mail) throws MessagingException {
		// Default to ISO-8859-1 (Western Latin 1)
		String charset = "ISO-8859-1";

		// Check whether the part contained a charset in the content-type header
		//
		/*
		 * text/html; charset="gb2312"
		 */
		StringTokenizer tok2 = new StringTokenizer(mail.getContentType(), ";=");
		String blah = tok2.nextToken();
		if (tok2.hasMoreTokens()) {
			blah = tok2.nextToken().trim();
			if (blah.toLowerCase().equals("charset") && tok2.hasMoreTokens()) {
				charset = tok2.nextToken().trim();
			}
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		dumpRawMsgToStream(mail, baos);
		try {
			return baos.toString(charset);
		} catch (UnsupportedEncodingException e) {
			throw new MessagingException(
					"Mail source's encoding is NOT supported!", e);
		}
	}

	private static void handleMultiPart(WebMail result, Multipart parts)
			throws MessagingException, IOException {
		for (int i = 0; i < parts.getCount(); i++) {
			BodyPart bodyPart = parts.getBodyPart(i);
			// Must be MimeBodyPart
			assert MimeBodyPart.class.isInstance(bodyPart);
			MimeBodyPart mimeBodyPart = (MimeBodyPart) bodyPart;

			// find text/plain or text/html
			if (isPlainText(mimeBodyPart.getContentType())
					|| isHtml(mimeBodyPart.getContentType())) {

				// check whether it is attachment
				if (mimeBodyPart.getDisposition() != null
						&& mimeBodyPart.getDisposition().toLowerCase()
								.equals(Part.ATTACHMENT)) {
					/*
					 * This is the attachment in text/plain or text/html E.g.
					 * Content-Disposition: attachment;
					 * filename="helpheader.htm"
					 */
					result.addAttachment(mimeBodyPart);
					continue;
				}

				// not attachment
				if (result.getDisplayBody() == null) { // no display body, make
														// this one as
					// the body to be displayed
					result.setDisplayBody(mimeBodyPart);
				} else {
					// prefer html display body. This handles the
					// multipart/alternative case
					if (!result.getDisplayBody().isMimeType("text/html")
							&& mimeBodyPart.isMimeType("text/html")) {
						result.setDisplayBody(mimeBodyPart);
					}
				}
			} else if (mimeBodyPart.getContentType().toLowerCase()
					.startsWith("multipart")) {
				handleMultiPart(result,
						(MimeMultipart) mimeBodyPart.getContent());
			} else if (mimeBodyPart.getContentType().toLowerCase()
					.startsWith("message")) {
				// handles the message/rfc822, which is the embedded email
				result.addAttachment(mimeBodyPart);
			} else {

				/*
				 * This is the embedded media
				 * 
				 * Content-Type: image/gif; name="next.gif"
				 * Content-Transfer-Encoding: base64 Content-ID:
				 * <__0@Foxmail.net>
				 */
				// FIXME implement content-location header look-up

				if (mimeBodyPart.getContentID() != null) {
					result.addEmbedMedias(mimeBodyPart);
					continue;
				}

				/*
				 * This is the attachment NOT in text/plain or text/html E.g.
				 * Content-Type: application/octet-stream; name="helpheader.htm"
				 * Content-Transfer-Encoding: base64 Content-Disposition:
				 * attachment; filename="helpheader.htm"
				 */
				if (mimeBodyPart.getDisposition() != null
						&& mimeBodyPart.getDisposition().toLowerCase()
								.equals(Part.ATTACHMENT)) {//TODO test the disposition
					result.addAttachment(mimeBodyPart);
					continue;
				}
			}
		}
	}

	public static boolean isPlainText(String contentType) {
		return contentType.toLowerCase().startsWith("text/plain");
	}

	public static boolean isHtml(String contentType) {
		/*
		 * text/html; charset="gb2312"
		 */
		return contentType.toLowerCase().startsWith("text/html");
	}

	public static boolean isMultipart(String contentType) {
		return contentType.toLowerCase().startsWith("multipart");
	}

}
