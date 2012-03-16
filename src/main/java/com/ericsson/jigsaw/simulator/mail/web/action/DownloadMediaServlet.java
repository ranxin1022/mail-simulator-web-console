package com.ericsson.jigsaw.simulator.mail.web.action;

import java.io.IOException;
import java.io.InputStream;

import javax.mail.Message;
import javax.mail.internet.MimeBodyPart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ericsson.jigsaw.simulator.mail.web.control.mail.receive.ReceiveMail;
import com.ericsson.jigsaw.simulator.mail.web.model.User;
import com.ericsson.jigsaw.simulator.mail.web.model.WebMail;
import com.ericsson.jigsaw.simulator.mail.web.util.MimeMailUtil;




/**
 * Servlet implementation class HandleAttachments
 */
public class DownloadMediaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadMediaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("application/x-download");
        
        String userId = request.getParameter("id");
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));  
        String contentId = request.getParameter("contentId"); 
        
        ReceiveMail receiveMail = new ReceiveMail();
        User user = new User(userId);
        
        try{

            Message message = receiveMail.getOneMail(user, subjectId); 
            WebMail mail = MimeMailUtil.parse(message);
            for(MimeBodyPart media: mail.getEmbedMedias()) {
            	String media_contentId=media.getContentID().replace("<", "").replace(">", "");
            	if(!media_contentId.equals(contentId)) {
            		continue;
            	}
            
            	response.setContentType(media.getContentType());
            	InputStream inputStream = media.getInputStream();
            	byte[] buffer = new byte[1024];
            	int readBytes = 0;
            	while((readBytes=inputStream.read(buffer)) != -1) {
            		response.getOutputStream().write(buffer, 0, readBytes);
            	}
            }
            
        }catch(Exception e){  
        	request.setAttribute("errorInfo",e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
        }  
        
	}

}
