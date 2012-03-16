package com.ericsson.jigsaw.simulator.mail.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ericsson.jigsaw.simulator.mail.web.control.user.UserControlImpl;
import com.ericsson.jigsaw.simulator.mail.web.model.User;


/**
 * Servlet implementation class ListUsers
 */
public class ManageUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageUsersServlet() {
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
				String domain = addUsername.substring(addUsername.indexOf("@"));
				String newAddUsername = addUsername.substring(0,addUsername.indexOf("@")).toLowerCase()+domain;
				newUser = new User(newAddUsername);
				userControlImpl.addUser(newUser);
			}
			
			if(delUsername!=null){
				 newUser = new User(delUsername);
				 userControlImpl.delUser(newUser);
			}
			
			String[] usernames = userControlImpl.listUsernames();
			request.setAttribute("allUsers", usernames);
			request.getRequestDispatcher("ManageUser.jsp").forward(request, response);
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
