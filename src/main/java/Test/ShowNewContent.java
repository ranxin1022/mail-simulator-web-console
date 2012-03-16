package Test;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import control.mail.receive.ReceiveMail;

import model.User;

/**
 * Servlet implementation class ShowContent
 */
public class ShowNewContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StringBuffer bodytext = new StringBuffer();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowNewContent() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=gbk");  
        String userId = request.getParameter("id");
		int subjectId = Integer.parseInt(request.getParameter("subjectId"));
		User u = new User(userId);
		ReceiveMail receiveMail = new ReceiveMail();
		try{
		    Message message = receiveMail.getOneMail(u, subjectId); 
		    Part part = (Part)message;
		    String contenttype = part.getContentType(); 
		    int nameindex = contenttype.indexOf("name"); 
		    boolean conname =false; 
	        if(nameindex != -1) conname=true; 
	        System.out.println("CONTENTTYPE: "+contenttype); 
	        System.out.println("conname:"+conname);
	        
	        if(part.isMimeType("text/plain")||part.isMimeType("text/html") && !conname){ 
	            bodytext.append((String)part.getContent()); 
	        }else if(part.isMimeType("multipart/alternative")){
	        	Multipart multipart = (Multipart)part.getContent(); 
	            int counts = multipart.getCount(); 
	            for(int i=0;i<counts;i++){ 
	                if(multipart.getBodyPart(i).isMimeType("text/html")){
	                	bodytext.append(multipart.getBodyPart(i)); 
	                }
	            } 
	        }else if((part.isMimeType("multipart/related"))){
	        	
	        	Multipart multipart = (Multipart)part.getContent(); 
	            int counts = multipart.getCount(); 
	            for(int i=0;i<counts;i++){ 
	            	String filename = multipart.getBodyPart(i).getFileName();
	            	System.out.println("filename:"+multipart.getBodyPart(i).getFileName());
	            	System.out.println("Disposition:"+multipart.getBodyPart(i).getDisposition());
	            	BodyPart bodypart = multipart.getBodyPart(i); 
	            	OutputStream output = null;
	            	InputStream input = null;
	                if(filename!=null){
	                	response.setHeader("Content-Disposition",   
	                             "attachment;filename=" + filename);  
		                output = response.getOutputStream();
		                input = bodypart.getInputStream();  
		                byte[] b= new byte[1024];
		                int j = 0;
		                while((j = input.read(b)) > 0 ){  
		                    output.write(b,0,j);  
		                }  
	                }
	                input.close();
	                output.flush();
	                output.close();
	            } 
	        	
	        }else if((part.isMimeType("multipart/*"))){
	        	
	            Multipart multipart = (Multipart)part.getContent(); 
	            int counts = multipart.getCount(); 
	            for(int i=0;i<counts;i++){ 
	            	BodyPart bodypart = multipart.getBodyPart(i);
	            	System.out.println("bodypart:"+multipart.getBodyPart(i).getContent());
	            	if(!bodypart.isMimeType("multipart/mixed")&& bodypart.getDisposition() == null){
	            		bodytext.append(multipart.getBodyPart(i)); 
	                }
	            } 
	        }else if(part.isMimeType("message/rfc822")){ 
	            //getMailContent((Part)part.getContent()); 
	        }else{} 
        }catch(Exception e){
        	request.setAttribute("errorInfo",e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
