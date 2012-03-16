package util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionFilter implements Filter {
	
	private String errorPage;
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpServletRequest request = (HttpServletRequest) arg0;
		
		try{
			chain.doFilter(arg0, arg1);
		}catch(Exception e){
			request.setAttribute("errorInfo", e.getMessage());
			System.out.println("emessage:"+e.getMessage());
			request.getRequestDispatcher(errorPage).forward(request, response);
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		
		errorPage = arg0.getInitParameter("errorPage");
		
	}

}
