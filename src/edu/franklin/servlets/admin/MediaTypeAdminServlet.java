package edu.franklin.servlets.admin;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.franklin.beans.MediaType;
import edu.franklin.models.MediaDAO;

@WebServlet(name="mediaTypeAdmin", urlPatterns = {"/admin/mediaTypeAdmin"})
public class MediaTypeAdminServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -4390579450009334207L;

    String URL = "";
    String dbName = "";
    String dbPassword = "";

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
    }

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        String action = request.getParameter("action");

        System.out.println("doGet:" + action);

        switch(action) {
            case "getAllMediaTypes":
                this.getAllMediaTypes(request, response);
                break;
            case "getMediaType":
            	this.getMediaType(request, response);
            	break;
            case "deleteMediaType":
            	this.deleteMediaType(request, response);
            	break;
            case "updateMediaType":
            	this.updateMediaType(request, response);
            	break;
            case "insertMediaType":
            	this.insertMediaType(request, response);
            	break;
            default:
                RequestDispatcher rd =
                request.getRequestDispatcher("/error-404.jsp");

                rd.forward(request, response);
                break;
        }

    }

    public void getAllMediaTypes(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        HttpSession session = request.getSession(true);

        String mediaTypeItemName = request.getParameter("mediaTypeItemName");

        MediaDAO mdAO = new MediaDAO(this.URL, this.dbName, this.dbPassword);
        mdAO.setURL(this.URL);
        mdAO.setDbName(this.dbName);
        mdAO.setDbPassword(this.dbPassword);

        System.out.println("I MADE IT TO getAllMediaTypes");

        session.setAttribute("mediaTypeList", mdAO.returnAllMediaTypes(mediaTypeItemName));
        
        System.out.println("I passed setAtribute");


        RequestDispatcher rd =
                request.getRequestDispatcher("/admin/mediaTypeItemListDisplay.jsp");

        rd.forward(request, response);
    }

    public void getMediaType(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

    	System.out.println("inside getMediaType");
    	
        HttpSession session = request.getSession(true);

        int ID = Integer.parseInt(request.getParameter("ID"));

        System.out.println("ID:" + ID);

        MediaDAO mdAO = new MediaDAO(this.URL, this.dbName, this.dbPassword);
        mdAO.setURL(this.URL);
        mdAO.setDbName(this.dbName);
        mdAO.setDbPassword(this.dbPassword);

        session.setAttribute("mediaTypeList", mdAO.returnMediaType(ID));
        
        System.out.println("HELLO WORLD");

        RequestDispatcher rd =
                request.getRequestDispatcher("/admin/mediaTypeItemDetailUpdate.jsp");

        System.out.println("HELLO WORLD 2");

        rd.forward(request, response);
    }
    
    public void deleteMediaType(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        HttpSession session = request.getSession(true);

        int ID = Integer.parseInt(request.getParameter("ID"));

        System.out.println("ID:" + ID);

        MediaDAO mdAO = new MediaDAO(this.URL, this.dbName, this.dbPassword);
        mdAO.setURL(this.URL);
        mdAO.setDbName(this.dbName);
        mdAO.setDbPassword(this.dbPassword);

        mdAO.deleteMediaType(ID);
        
        System.out.println("before deleteMediaType servlet");
        
        session.removeAttribute("mediaTypeList");

        System.out.println("after deleteMediaType servlet");
        
        RequestDispatcher rd =
                request.getRequestDispatcher("/admin/mediaTypeSearch.jsp");

        rd.forward(request, response);
        
        return;
    }

    public void updateMediaType(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        HttpSession session = request.getSession(true);

        MediaDAO mdAO = new MediaDAO(this.URL, this.dbName, this.dbPassword);
        mdAO.setURL(this.URL);
        mdAO.setDbName(this.dbName);
        mdAO.setDbPassword(this.dbPassword);

        MediaType mt = new MediaType();
        mt.setMediaID(Integer.parseInt(request.getParameter("mediaID")));
        mt.setMediaTypeDescription(request.getParameter("mediaTypeDescription"));

        
        System.out.println("MediaTypeID:" + mt.getMediaID());
        System.out.println("MediaTypeDescription:" + mt.getMediaTypeDescription());
        
        mdAO.updateMediaType(mt);
        
        session.removeAttribute("mediaTypeList");
        
        RequestDispatcher rd =
                request.getRequestDispatcher("/admin/mediaTypeSearch.jsp");

        rd.forward(request, response);
        
        return;
    }
    
    public void insertMediaType(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        HttpSession session = request.getSession(true);

        MediaDAO mdAO = new MediaDAO(this.URL, this.dbName, this.dbPassword);
        mdAO.setURL(this.URL);
        mdAO.setDbName(this.dbName);
        mdAO.setDbPassword(this.dbPassword);

        MediaType mt = new MediaType();
                
        mt.setMediaTypeDescription(request.getParameter("mediaTypeDescription"));
        
        System.out.println("mediaTypeDescription =" + mt.getMediaTypeDescription());
        
        mdAO.insertMediaType(mt);
        
        session.removeAttribute("mediaTypeList");
        
        RequestDispatcher rd =
                request.getRequestDispatcher("/admin/mediaTypeSearch.jsp");

        rd.forward(request, response);
        
        return;
    }
}
