package Test;


import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import util.MailSession;

import model.Mail;
public class SendMailService {

	private Session session = null;
	private Mail mail;
	public SendMailService(Mail mail) {
		this.mail = mail;
	}


	private void setMimeMessageInfo(MimeMessage message)
			throws AddressException, MessagingException
			{
		if (mail.getFrom() != null && !"".equals(mail.getFrom())) {
			message.setFrom(new InternetAddress(mail.getFrom()));
			message.setSender(new InternetAddress(mail.getFrom()));
		}
		if (mail.getTo() != null && !"".equals(mail.getTo()))
			message.setRecipients(RecipientType.TO,
					InternetAddress.parse(mail.getTo()));
		if (mail.getCc() != null && !"".equals(mail.getCc()))
			message.setRecipients(RecipientType.CC,
					InternetAddress.parse(mail.getCc()));
		
		if (mail.getSubject() != null && !"".equals(mail.getSubject()))
			message.setSubject(mail.getSubject(), "UTF-8");
		
		if (mail.getContent() != null && !"".equals(mail.getContent()))
			message.setText(mail.getContent(), "UTF-8");
		if(mail.getDate()!=null&&!"".equals(mail.getContent())){
			message.setSentDate(mail.getDate());
		}else{
			message.setSentDate(new Date());
		}
	}

	public void sendMail(MimeMessage message) throws AddressException,
		MessagingException{
		
		Transport.send(message);
	}
	public void sendMail() throws AddressException, MessagingException
		 {
		MimeMessage message = createMimeMessage();
		Transport.send(message);
	}	
	
	public MimeMessage createMimeMessage() throws AddressException,
		MessagingException {
		session = MailSession.createSession(
			"util/email.properties");
		
		MimeMessage message = new MimeMessage(session);
		setMimeMessageInfo(message);
		//message.saveChanges();
		return message;
		}
	}
