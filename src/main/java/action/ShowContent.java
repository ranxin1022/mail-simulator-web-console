package action;

import java.io.IOException;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import control.mail.receive.ReceiveMail;

import model.User;

/**
 * Servlet implementation class ShowContent
 */
public class ShowContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowContent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   	 response.setContentType("text/html;charset=gbk");  
		 ServletOutputStream servletOutput = response.getOutputStream();
		 String userId = request.getParameter("id");
		 int subjectId = Integer.parseInt(request.getParameter("subjectId"));
		 User u = new User(userId);
		 ReceiveMail receiveMail = new ReceiveMail();
		 try{  
	            Message message = receiveMail.getOneMail(u, subjectId); 
	            if(!message.isMimeType("multipart/mixed")){
	            	
	            	response.setContentType("message/rfc822");
	            	message.writeTo(servletOutput);
	            	
	            	
	            }else{
	            		
	            	Multipart multipart = (Multipart)message.getContent();
	            	int bodyCounts = multipart.getCount();
	            	for(int i = 0; i < bodyCounts; i++){                      
	            		BodyPart bodypart = multipart.getBodyPart(i);
	            		if(!bodypart.isMimeType("multipart/mixed")&& bodypart.getDisposition() == null){                          
	            			response.setContentType("message/rfc822");
	            			bodypart.writeTo(servletOutput); 
	            			//System.out.println("bodypart:"+bodypart);
	            		}                  
	            	}  
	            }
	           
	            receiveMail.close();
	            servletOutput.close();
	            
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
