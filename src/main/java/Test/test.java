package Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;

import control.mail.receive.ReceiveMail;

import model.User;

public class test {

	/**
	 * @param args
	 */
	/*
	public static String getpart(Part part, int partnum,int x) throws MessagingException,IOException
	{
		String s=""; 
		String s1=""; 
		String s2="";
		String s5="";
		String sct = part.getContentType();
		if (sct == null) 
		{ 
			s="part invalid";
			return s;
		} 
	
		if(part.isMimeType("multipart/alternative")){ 
			
			String s6="";
			String s7="";
			Multipart mp = (Multipart)part.getContent();
			int count = mp.getCount();
			for (int i = 0; i < count; i++) 
			{ 
		
			if(mp.getBodyPart(i).isMimeType("text/plain"))
				s7=getpart(mp.getBodyPart(i), i, 3,2);
			if(mp.getBodyPart(i).isMimeType("text/html"))
				s6=getpart(mp.getBodyPart(i), i, 3,1); 
			} 
			if(x==1){s5=s6;} 
			
			if(x==2){s5=s7;}
			return s5;
		}
		s=s1+s2; 
		return s; 
	
		

	}*/
	public static void main(String[] args) {

		User user = new User("abc@xinxin.com");
		try {
			ReceiveMail mail = new ReceiveMail();
			mail.getMailList(user);
			Message m = mail.getOneMail(user,13);
			
			System.out.println(m.getSentDate());
			Part messagePart = m;
			Object content = messagePart.getContent();
			if(content instanceof Multipart){
				messagePart = ((Multipart)content).getBodyPart(0);
				System.out.println("[Multipart Message]");
			}
			String contentType = messagePart.getContentType();
			System.out.println("Content:"+contentType);
			if(contentType.startsWith("text/plain")||contentType.startsWith("text/plain")){
				
				InputStream is = messagePart.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				String thisLine = reader.readLine();
				while(thisLine!=null){
					System.out.println(thisLine);
					thisLine = reader.readLine();
				}
			}
			
			System.out.println("-----------------END---------------");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
