package action;

import java.io.IOException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.User.UserControlImpl;
import control.mail.receive.ReceiveMail;

import model.User;

/**
 * Servlet implementation class ShowUserMails
 */
public class ShowUserMails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowUserMails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html;charset=gbk");  
		String username = request.getParameter("id");
		ReceiveMail receiveMail = new ReceiveMail();
		String msg = null;
		try{
			if(username==null){
				UserControlImpl userControlImpl = new UserControlImpl();
				if(userControlImpl.listUsernames()[0]!=null){
					
					username = userControlImpl.listUsernames()[0];
				}else{
					
					msg = "there is no user";
	
				}
				
				if(msg!=null){
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("showAllMails.jsp").forward(request, response);
				}
			}
			
			User user = new User(username);	
			int subjectId = 0;
			if(request.getParameter("subjectId")!=null){
				try {
					subjectId = Integer.parseInt(request.getParameter("subjectId"));
					receiveMail.deleteMail(user,subjectId);
				} catch (MessagingException e) {
					
					receiveMail.close();
					request.setAttribute("errorInfo",e.getMessage());
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			}
				
			try {
				Message[] messages = receiveMail.getMailList(user);
				request.setAttribute("messages", messages);
				request.setAttribute("username", username);
				request.getRequestDispatcher("showAllMails.jsp").forward(request, response);
					
			} catch (Exception e) {
				
				receiveMail.close();
				request.setAttribute("errorInfo",e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
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
