package Test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import control.User.UserControlImpl;

/**
 * Servlet implementation class ListUsers
 */
public class ListUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=gbk");  

		String addUsername = request.getParameter("addUsername");
        String delUsername = request.getParameter("delUsername");
        UserControlImpl userControlImpl = new UserControlImpl();
        User newUser = null;
        try{
			if(addUsername!=null){
				newUser = new User(addUsername);
				userControlImpl.addUser(newUser);
			}
			
			if(delUsername!=null){
				 newUser = new User(delUsername);
				userControlImpl.delUser(newUser);
			}
			
			String[] usernames = userControlImpl.listUsernames();
			
			request.setAttribute("allUsers", usernames);
			request.getRequestDispatcher("operateUser.jsp").forward(request, response);
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
