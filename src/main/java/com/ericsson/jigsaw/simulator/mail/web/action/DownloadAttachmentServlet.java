package com.ericsson.jigsaw.simulator.mail.web.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import javax.mail.Message;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeUtility;
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
public class DownloadAttachmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadAttachmentServlet() {
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
        int bodynum = Integer.parseInt(request.getParameter("bodynum"));    
        String filename = request.getParameter("filename");  
       
        ReceiveMail receiveMail = new ReceiveMail();
        User user = new User(userId);
       
        try{
        	String newFilename = null;
        	if(filename.startsWith("=?")){
        		newFilename= new Date().getTime()+MimeUtility.decodeText(filename).substring(MimeUtility.decodeText(filename).lastIndexOf("."));
        	}else{
        		newFilename = filename;
        	}
        	
            Message message = receiveMail.getOneMail(user, subjectId); 
            WebMail webMail = MimeMailUtil.parse(message); 
            response.setHeader("Content-Disposition",   
                "attachment;filename=" + newFilename);  
            
            MimeBodyPart bodypart = webMail.getAttachments().get(bodynum);
            
            OutputStream output = response.getOutputStream();
            InputStream input = bodypart.getInputStream();  
            byte[] b= new byte[1024];
            int i = 0;
            while((i = input.read(b)) > 0 ){  
                output.write(b,0,i);  
            }  
            input.close();
            output.flush();
            output.close();
        }catch(Exception e){  
        	request.setAttribute("errorInfo",e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
        }  
	}

}
