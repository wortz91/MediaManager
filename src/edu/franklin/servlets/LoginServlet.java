package edu.franklin.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.franklin.models.MediaDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="login", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String userIn = "admin";
    private final String pwdIn = "password";

    String URL = "";
    String dbName = "";
    String dbPassword = "";
    int defaultSessionLength = 17;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ServletContext context = this.getServletContext();

        this.URL = context.getInitParameter("url");
        System.out.println("URL is:" + this.URL);

        this.dbName = context.getInitParameter("name");
        System.out.println("dbName is:" + this.dbName);

        this.dbPassword = context.getInitParameter("password");
        System.out.println("dbPassword is:" + this.dbPassword);
        
        this.defaultSessionLength = Integer.parseInt(context.getInitParameter("defaultSessionLength"));
        System.out.println("defaultSessionLength is:" + this.defaultSessionLength);
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        // get request parameters for userID and password
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        System.out.println("user:" + user);
        System.out.println("pwd:" + pwd);
        
        MediaDAO loginDAO = new MediaDAO(this.URL, this.dbName, this.dbPassword);

        HttpSession session = request.getSession();
        
        if(loginDAO.verifyAuthentication(user, pwd)){
            session.setAttribute("isAdmin", "true");
            
            //setting session to expiry in 2 mins
            session.setMaxInactiveInterval(2*60);
            
            Cookie userName = new Cookie("user", user);
            
            userName.setMaxAge(30*60);
            
            response.addCookie(userName);
            
            System.out.println("Before sendRedirect in doPost");
            
            response.sendRedirect("/MediaManagerFinal/admin/index.jsp");
            
        } else {
            session.setAttribute("isAdmin", "false");

            System.out.println("admin false");
            
            response.sendRedirect("/MediaManagerFinal/admin/index.jsp");
            
//            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/MediaManagerFinal/login.jsp");
//            
//            PrintWriter out= response.getWriter();
//            
//            out.println("<font color=red>Either user name or password is wrong.</font>");
//            
//            rd.include(request, response);
        }

    }

}