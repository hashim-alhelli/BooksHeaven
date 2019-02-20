package filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class Throttle
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST },
urlPatterns = { "/Start","/Start/*"}
					, servletNames = { "Start" })
public class Throttle implements Filter {

	 private FilterConfig filterConfig = null;
	
    /**
     * Default constructor. 
     */
    public Throttle() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		
		
			HttpServletRequest r = (HttpServletRequest) request;
		
		//System.out.println((System.currentTimeMillis() - r.getSession().getLastAccessedTime())/1000);
	
if (filterConfig == null)
    return;


    
	if(filterConfig. getServletContext().getAttribute("hitcounter")!=null){
		
		if((int)filterConfig. getServletContext().getAttribute("hitcounter")==3){
			r.getRequestDispatcher("/OrderSubmitted.jspx").forward(request, response);
			filterConfig. getServletContext().removeAttribute("hitcounter");
			r.getSession().removeAttribute("orderstatus");
		}
		else{
			chain.doFilter(request, response);
		}
	}
	else{
		chain.doFilter(request, response);
	}
		System.out.println("hit counter is " + filterConfig. getServletContext().getAttribute("hitcounter"));
	// pass the request along the filter chain
		
	//	}//
		
		
		
		
		//chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	this.filterConfig = fConfig;
	
	}

}
