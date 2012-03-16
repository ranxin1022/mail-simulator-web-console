package com.ericsson.jigsaw.simulator.mail.web.action;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ericsson.jigsaw.simulator.mail.web.control.domain.DomainControlImpl;
import com.ericsson.jigsaw.simulator.mail.web.model.Domain;


/**
 * Servlet implementation class ListDomains
 */
public class ManageDomainsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageDomainsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=gbk");  

		String addDomain = request.getParameter("addDomain");
        String delDomain = request.getParameter("delDomain");
        DomainControlImpl domainControlImpl = new DomainControlImpl();
        Domain newDomain = null;
        try{
			if(addDomain!=null){
				newDomain = new Domain(addDomain);
				domainControlImpl.addDomain(newDomain);
			}
			
			if(delDomain!=null){
				newDomain = new Domain(delDomain);
				domainControlImpl.delDomain(newDomain);
			}
			
			String[] domains = domainControlImpl.listDomains();
			
			request.setAttribute("allDomains", domains);
			request.getRequestDispatcher("ManageDomain.jsp").forward(request, response);
        }catch(Exception e){
        	System.out.println(e.getMessage());
        	String errorInfo = e.getMessage();
        	if(e.getMessage().equals("Unable to add domain "+addDomain)){
        		errorInfo = addDomain+" has add, you can not add again!";
        	}
        	request.setAttribute("errorInfo", errorInfo);			
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
