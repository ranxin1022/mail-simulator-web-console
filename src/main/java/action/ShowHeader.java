package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.MimeUtility;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import control.mail.receive.ReceiveMail;

/**
 * Servlet implementation class ShowHeader
 */
public class ShowHeader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowHeader() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=gbk");  
	        PrintWriter out = response.getWriter();  
	 
	        String userId = request.getParameter("id");
			int subjectId = Integer.parseInt(request.getParameter("subjectId"));
			
			User u = new User(userId);
			ReceiveMail receiveMail = new ReceiveMail();
	        try{                      
	            Message message = receiveMail.getOneMail(u, subjectId);
	            String from = (message.getFrom()[0]).toString(); 
	            String to = (message.getAllRecipients()[0]).toString();
	            String subject = message.getSubject();  
	            String sentDate = null;
	            if(message.getSentDate()==null){
	            	sentDate = new Date().toString();
	            }else{
	            	sentDate = DateFormat.getInstance().format(message.getSentDate());  
	            
	            }
	            out.println("subject:" + subject + "<br/>");  
	            out.println("From:" + from + "<br/>");  
	            out.println("To:"+to+"<br/>");
	            out.println("sendDate:" + sentDate + "<br/>");  
	              
	            if(message.isMimeType("multipart/*"))  
	            {  
	                Multipart multipart = (Multipart)message.getContent();  
	                int bodyCounts = multipart.getCount();  
	                for(int i = 0; i < bodyCounts; i++)  
	                {  
	                    BodyPart bodypart = multipart.getBodyPart(i);  
	                    if(bodypart.getDisposition() != null)  
	                    {  
	                        String filename = bodypart.getFileName();  
	                        String downloadFileName = filename.replace(" ", "_");
	                        
	                        if(filename.startsWith("=?"))  
	                        {  
	                            filename = MimeUtility.decodeText(filename);  
	                        }  
	                        //System.out.println("fileName:"+filename);
	                       
	                        out.print("<B>attachment " + (i+1) + ":</B>");  
	                        out.println("<a href=HandleAttachments?id="+userId+"&subjectId="   
	                            + subjectId+ "&bodynum=" + i + "&filename=" 
	                            + downloadFileName + ">" + filename + "</a></br>");  
	                    }  
	                }  
	            }
	            receiveMail.close();
	            out.close();
	        }catch(Exception e){  
	        	request.setAttribute("errorInfo",e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request, response);
	        }  
	     
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
