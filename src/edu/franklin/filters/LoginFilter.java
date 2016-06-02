package edu.franklin.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

    private ServletContext cont;
    /**
     * Default constructor.
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    @Override
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
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        System.out.println("doFilter");

        HttpSession session = req.getSession(false);
        
        String isAdmin = "false";
        
		try {
			isAdmin = (String) session.getAttribute("isAdmin");
			
			if(!isAdmin.equals("true")){
	            this.cont.log("Unauthorized access request");
	            res.sendRedirect("/MediaManagerFinal/login.jsp");
	        } else {
	            // pass the request along the filter chain
	            chain.doFilter(request, response);
	        }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
			this.cont.log("Unauthorized access request");
			res.sendRedirect("/MediaManagerFinal/login.jsp");
		}
        
        System.out.println("isAdmin:" + isAdmin);

        

    }

    /**
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
        this.cont = fConfig.getServletContext();
        this.cont.log("AunticationFilter initialized");
    }

}
