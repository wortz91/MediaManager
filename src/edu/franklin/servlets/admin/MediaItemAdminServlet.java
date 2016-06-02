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

import edu.franklin.beans.MediaItem;
import edu.franklin.models.MediaDAO;

@WebServlet(name="mediaItemAdmin", urlPatterns = {"/admin/mediaItemAdmin"})
public class MediaItemAdminServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
        this.init();

        String action = request.getParameter("action");

        System.out.println("doGet:" + action);

        //ROUTER
        switch(action) {
            case "getAllItems":
                this.getAllItems(request, response);
                break;
            case "getItemDetail":
                this.getItemDetail(request, response);
                break;
            case "deleteItemDetail":
            	this.deleteItemDetail(request, response);
            	break;
            case "updateItemDetail":
            	this.updateItemDetail(request, response);
            	break;
            case "insertItemDetail":
            	this.insertItemDetail(request, response);
            	break;
            case "insertItemDetailSetUp":
            	System.out.println("in router before method call");
            	this.insertItemDetailSetUp(request, response);
            	System.out.println("in router after method call");
            	break;
            default:
                RequestDispatcher rd =
                request.getRequestDispatcher("/error-404.jsp");

                rd.forward(request, response);
                break;
        }

    }

    public void getAllItems(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        HttpSession session = request.getSession(true);

        String mediaItemName = request.getParameter("mediaItemName");

        MediaDAO mdAO = new MediaDAO(this.URL, this.dbName, this.dbPassword);
        mdAO.setURL(this.URL);
        mdAO.setDbName(this.dbName);
        mdAO.setDbPassword(this.dbPassword);


        session.setAttribute("mediaItemList", mdAO.returnAllMediaItems(mediaItemName));

        RequestDispatcher rd =
                request.getRequestDispatcher("/admin/mediaItemListDisplay.jsp");

        rd.forward(request, response);
    }

    public void getItemDetail(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        HttpSession session = request.getSession(true);

        int ID = Integer.parseInt(request.getParameter("ID"));

        System.out.println("ID:" + ID);

        MediaDAO mdAO = new MediaDAO(this.URL, this.dbName, this.dbPassword);
        mdAO.setURL(this.URL);
        mdAO.setDbName(this.dbName);
        mdAO.setDbPassword(this.dbPassword);

        session.setAttribute("mediaItemList", mdAO.returnMediaItemDetail(ID));
        
        session.setAttribute("genreList", mdAO.returnGenreItemsCount());
                
        session.setAttribute("mediaTypeList", mdAO.returnMediaTypeCount());

        RequestDispatcher rd =
                request.getRequestDispatcher("/admin/mediaItemDetailUpdate.jsp");

        rd.forward(request, response);
    }
    
    public void deleteItemDetail(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        HttpSession session = request.getSession(true);

        int ID = Integer.parseInt(request.getParameter("ID"));

        System.out.println("ID:" + ID);

        MediaDAO mdAO = new MediaDAO(this.URL, this.dbName, this.dbPassword);
        mdAO.setURL(this.URL);
        mdAO.setDbName(this.dbName);
        mdAO.setDbPassword(this.dbPassword);

        mdAO.deleteMediaItemDetail(ID);
        
        session.removeAttribute("mediaItemList");

        RequestDispatcher rd =
                request.getRequestDispatcher("/admin/mediaItemSearch.jsp");

        rd.forward(request, response);
        
        return;
    }

    public void updateItemDetail(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        HttpSession session = request.getSession(true);

        MediaDAO mdAO = new MediaDAO(this.URL, this.dbName, this.dbPassword);
        mdAO.setURL(this.URL);
        mdAO.setDbName(this.dbName);
        mdAO.setDbPassword(this.dbPassword);

        MediaItem mi = new MediaItem();
        mi.setID(Integer.parseInt(request.getParameter("ID")));
        mi.setGenreID(Integer.parseInt(request.getParameter("genreID")));
        mi.setMediaTypeID(Integer.parseInt(request.getParameter("mediaTypeID")));
        mi.setName(request.getParameter("name"));
        mi.setYear(Integer.parseInt(request.getParameter("year")));
        mi.setComments(request.getParameter("comments"));
        mi.setCurrentValue(Double.parseDouble(request.getParameter("currentvalue")));
        
        System.out.println("ID:" + mi.getID());
        System.out.println("name:" + mi.getName());
        
        mdAO.updateMediaItemDetail(mi);
        
        session.removeAttribute("mediaItemList");
        
        RequestDispatcher rd =
                request.getRequestDispatcher("/admin/mediaItemSearch.jsp");

        rd.forward(request, response);
        
        return;
    }
    
    public void insertItemDetail(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        HttpSession session = request.getSession(true);

        MediaDAO mdAO = new MediaDAO(this.URL, this.dbName, this.dbPassword);
        mdAO.setURL(this.URL);
        mdAO.setDbName(this.dbName);
        mdAO.setDbPassword(this.dbPassword);

        MediaItem mi = new MediaItem();
        mi.setGenreID(Integer.parseInt(request.getParameter("genreID")));
        mi.setMediaTypeID(Integer.parseInt(request.getParameter("mediaTypeID")));
        mi.setName(request.getParameter("name"));
        mi.setYear(Integer.parseInt(request.getParameter("year")));
        mi.setComments(request.getParameter("comments"));
        mi.setCurrentValue(Double.parseDouble(request.getParameter("currentValue")));
                        
        mdAO.insertMediaItemDetail(mi);
        
        session.removeAttribute("mediaItemList");
        
        RequestDispatcher rd =
                request.getRequestDispatcher("/admin/mediaItemSearch.jsp");

        rd.forward(request, response);
        
        return;
    }

    public void insertItemDetailSetUp(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        HttpSession session = request.getSession(true);

        MediaDAO mdAO = new MediaDAO(this.URL, this.dbName, this.dbPassword);
        mdAO.setURL(this.URL);
        mdAO.setDbName(this.dbName);
        mdAO.setDbPassword(this.dbPassword);
        
        session.setAttribute("genreList", mdAO.returnGenreItemsCount());
                
        session.setAttribute("mediaTypeList", mdAO.returnMediaTypeCount());

        RequestDispatcher rd =
                request.getRequestDispatcher("/admin/mediaItemDetailInsert.jsp");

        rd.forward(request, response);
    }
}
