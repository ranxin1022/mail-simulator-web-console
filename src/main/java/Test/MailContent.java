package Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import control.mail.receive.ReceiveMail;

/**
 * Servlet implementation class MailContent
 */
public class MailContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private StringBuffer bodytext = new StringBuffer();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailContent() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void getMailContent(Part part,HttpServletResponse response)throws Exception{ 
    	
    	 if(part.isMimeType("text/plain")||part.isMimeType("text/html")){ 
             bodytext.append((String)part.getContent()); 
         }else if(part.isMimeType("multipart/alternative")){
         	Multipart multipart = (Multipart)part.getContent(); 
            int counts = multipart.getCount(); 
            for(int i=0;i<counts;i++){ 
             	System.out.println("bodypart:"+multipart.getBodyPart(i).getContentType());
                if(multipart.getBodyPart(i).isMimeType("text/html")){
                 	getMailContent(multipart.getBodyPart(i),response); 
                }
             } 
         }else if((part.isMimeType("multipart/related"))){
         	
         	Multipart multipart = (Multipart)part.getContent(); 
             int counts = multipart.getCount(); 
             for(int i=0;i<counts;i++){ 
            	 String filename = multipart.getBodyPart(i).getFileName();
	            	BodyPart bodypart = multipart.getBodyPart(i); 
	            	OutputStream output = null;
	            	InputStream input = null;
	            	if(multipart.getBodyPart(i).isMimeType("multipart/alternative")){
	            		System.out.print("related:contentType"+ multipart.getBodyPart(i).getContentType());

	                 	getMailContent(multipart.getBodyPart(i),response); 
	                 }else{
	                	response.setHeader("Content-Disposition",   
	                             "attachment;filename=" + filename);  
		                output = response.getOutputStream();
		                input = bodypart.getInputStream();  
		                byte[] b= new byte[1024];
		                int j = 0;
		                while((j = input.read(b)) > 0 ){  
		                    output.write(b,0,j);  
		                }  
		                input.close();
		                output.flush();
	                }
	                
             } 
         	
         }else if((part.isMimeType("multipart/*"))){
    
             Multipart multipart = (Multipart)part.getContent(); 
             int counts = multipart.getCount(); 
             for(int i=0;i<counts;i++){ 
                 getMailContent(multipart.getBodyPart(i),response); 
             } 
         }else if(part.isMimeType("message/rfc822")){ 
             getMailContent((Part)part.getContent(),response); 
         }else{} 
         
    	
    } 


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=gbk"); 
		ServletOutputStream out = response.getOutputStream();
		
		 String userId = request.getParameter("id");
		 int subjectId = Integer.parseInt(request.getParameter("subjectId"));
		 User u = new User(userId);
		 ReceiveMail receiveMail = new ReceiveMail();
		 try{  
	            Message message = receiveMail.getOneMail(u, subjectId); 
	            getMailContent(message,response);
	            System.out.println("result:"+bodytext.toString());
	            out.print(bodytext.toString());
	            bodytext = new StringBuffer("");
	            //receiveMail.close();
	            out.close();
	            
	        }catch(Exception e){  
	            e.printStackTrace();  
	        }
		 //request.getRequestDispatcher("hello.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
