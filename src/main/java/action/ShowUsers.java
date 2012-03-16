package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

import control.User.UserControlImpl;

/**
 * Servlet implementation class ShowUsers
 */
public class ShowUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=gbk");  
		UserControlImpl userControl = new UserControlImpl();
		try{
			List<User> users = userControl.listUsers();
			if(users==null){
				request.getRequestDispatcher("showUsers.jsp").forward(request, response);
			}else{
				request.setAttribute("users", users);
				request.getRequestDispatcher("showUsers.jsp").forward(request, response);
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
