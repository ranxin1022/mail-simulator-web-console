package Test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Domain;
import control.Domain.DomainControlImpl;

/**
 * Servlet implementation class ListDomains
 */
public class ListDomains extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListDomains() {
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
			request.getRequestDispatcher("operateDomain.jsp").forward(request, response);
        }catch(Exception e){
        	request.setAttribute("errorInfo", e.getMessage());			
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
