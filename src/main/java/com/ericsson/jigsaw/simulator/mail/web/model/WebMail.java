package com.ericsson.jigsaw.simulator.mail.web.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.mail.Message;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;

/**
 * A web mail 
 * @author exialia
 *
 */
public class WebMail {
	private Message message;
	private Part displayBody;
	private List<MimeBodyPart> attachments = new ArrayList<MimeBodyPart>();
	private List<MimeBodyPart> embedMedias = new ArrayList<MimeBodyPart>();

	public Message getMessage() {
		return message;
	}

	public Part getDisplayBody() {
		return displayBody;
	}

	public List<MimeBodyPart> getAttachments() {
		return Collections.unmodifiableList(this.attachments);
	}

	public List<MimeBodyPart> getEmbedMedias() {
		return Collections.unmodifiableList(embedMedias);
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public void setDisplayBody(Part displayBody) {
		this.displayBody = displayBody;
	}

	public void addAttachment(MimeBodyPart attachment) {
		this.attachments.add(attachment);
	}

	public void addEmbedMedias(MimeBodyPart embedMedia) {
		this.embedMedias.add(embedMedia);
	}
}
