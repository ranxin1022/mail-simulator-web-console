package control.mail.receive;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import util.MailSession;
import util.UtilTools;


import model.User;
public class ReceiveMail {

	private String username;
	private String password;
	private Store store;
	private String host = UtilTools.getHost();
	private Session session;
	private Folder folder ;
	
	public ReceiveMail(){}
	
	private void connection(User user) throws MessagingException {
		this.username = user.getName();
		this.password = user.getPassword();
		session = MailSession
				.getInstance("util/email.properties");
		store = session.getStore();
		store.connect(host, username, password);
		folder= store.getFolder("INBOX");
	}
	
	@SuppressWarnings("static-access")
	public void deleteMail(User u,int i)
			throws MessagingException {
		connection(u);
		folder.open(folder.READ_WRITE);
		Message[] messages = folder.getMessages();
		
		if(messages[i].isSet(Flags.Flag.DELETED)==false){
			messages[i].setFlag(Flags.Flag.DELETED, true);
		}
		
		folder.close(true);
		store.close();
	}
	
	@SuppressWarnings("static-access")
	public Message[] getMailList(User user) throws Exception {
		connection(user);
		folder.open(folder.READ_ONLY);
		Message[] messages = folder.getMessages();
		return messages;
	}
	
	@SuppressWarnings("static-access")
	public Message getOneMail(User user,int i) throws Exception{
		connection(user);
		folder.open(folder.READ_ONLY);
		Message[] messages = folder.getMessages();
		return messages[i];
	}
	public void close(){
		try{
			folder.close(true);
			store.close();
		}catch(MessagingException e){
			e.printStackTrace();
		}
	}

}
