package Servlets;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class SecureFilter
 */
@WebFilter(filterName = "SecureFilter",urlPatterns = {"/secured/*"})
public class SecureFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SecureFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("Filter()");
		if (((HttpServletRequest) request).getSession().getAttribute("user") != null)
		{
		    chain.doFilter(request, response); // User is logged in, just continue request.
		} else {
		    ((HttpServletResponse) response).sendRedirect("/bsnlproject/login.jsp"); // Not logged in, show login page. You can eventually show the error page instead.
		}
	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
