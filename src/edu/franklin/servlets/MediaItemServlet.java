package edu.franklin.servlets;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.franklin.beans.MediaItems;
import edu.franklin.models.MediaDAO;
import edu.franklin.models.MediaItemDAO;

@WebServlet(name="mediaItem", urlPatterns = {"/mediaItem"})
public class MediaItemServlet extends HttpServlet {
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

        switch(action) {
            case "getAllItems":
                this.getAllItems(request, response);
                break;
            case "getItemDetail":
                this.getItemDetail(request, response);
                break;
            case "getAllItemsJSON":
            	this.getAllItemsJSON(request, response);
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
                request.getRequestDispatcher("/mediaItemListDisplay.jsp");

        rd.forward(request, response);
    }

    public void getAllItemsJSON(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        HttpSession session = request.getSession(true);
        
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Content-Type", "application/json; charset=UTF-8");

        String mediaItemName = request.getParameter("mediaItemName");

        MediaItemDAO mdAO = new MediaItemDAO();
       
        MediaItems mediaItems = new MediaItems();
        
        mediaItems.setSearchCriteria(mediaItemName);
        
        mediaItems.setMediaItemList(mdAO.returnAllMediaItems(mediaItemName));        
        
    	ObjectMapper mapper = new ObjectMapper();

    	String JSON = mapper.writeValueAsString(mediaItems);
		System.out.println(JSON);

		PrintWriter out = response.getWriter();
	    out.println(JSON);
        
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

        RequestDispatcher rd =
                request.getRequestDispatcher("/mediaItemDetailDisplay.jsp");

        rd.forward(request, response);
    }
}
