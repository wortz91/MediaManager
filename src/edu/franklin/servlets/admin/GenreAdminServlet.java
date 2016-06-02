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

import edu.franklin.beans.Genre;
import edu.franklin.models.MediaDAO;

@WebServlet(name="genreItemAdmin", urlPatterns = {"/admin/genreItemAdmin"})
public class GenreAdminServlet extends HttpServlet {

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
        this.init();

        String action = request.getParameter("action");
        
        System.out.println("doGet:" + action);

        //ROUTER
        switch(action) {
            case "getAllGenres":
                this.getAllGenres(request, response);
                System.out.println("getAllGenres pressed");
                break;
            case "getItemGenre":
                this.getItemGenre(request, response);
                break;
            case "deleteGenreItem":
            	this.deleteGenreItem(request, response);
            	break;
            case "updateGenreDetail":
            	this.updateItemDetail(request, response);
            	break;
            case "insertGenreDetail":
            	this.insertGenreItemDetail(request, response);
            	break;
            default:
            	System.out.println("ERROR WILL ROBINSON, ERROR");
                RequestDispatcher rd =
                request.getRequestDispatcher("/error-404.jsp");

                rd.forward(request, response);
                break;
        }

    }

    public void getAllGenres(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        HttpSession session = request.getSession(true);

        String genreItemName = request.getParameter("genreItemName");

        MediaDAO mdAO = new MediaDAO(this.URL, this.dbName, this.dbPassword);
        mdAO.setURL(this.URL);
        mdAO.setDbName(this.dbName);
        mdAO.setDbPassword(this.dbPassword);

        System.out.println("I MADE IT TO getAllGenres");

        session.setAttribute("genreItemList", mdAO.returnAllGenreItems(genreItemName));
        
        System.out.println("I passed setAtribute");


        RequestDispatcher rd =
                request.getRequestDispatcher("/admin/genreItemListDisplay.jsp");

        rd.forward(request, response);
    }

    public void getItemGenre(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        HttpSession session = request.getSession(true);

        int ID = Integer.parseInt(request.getParameter("ID"));

        System.out.println("ID:" + ID);

        MediaDAO mdAO = new MediaDAO(this.URL, this.dbName, this.dbPassword);
        mdAO.setURL(this.URL);
        mdAO.setDbName(this.dbName);
        mdAO.setDbPassword(this.dbPassword);

        session.setAttribute("genreItemList", mdAO.returnGenreItem(ID));
        
        System.out.println("HELLO WORLD");

        RequestDispatcher rd =
                request.getRequestDispatcher("/admin/genreItemDetailUpdate.jsp");

        System.out.println("HELLO WORLD 2");

        rd.forward(request, response);
    }
    
    public void deleteGenreItem(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        HttpSession session = request.getSession(true);

        int ID = Integer.parseInt(request.getParameter("ID"));

        System.out.println("ID:" + ID);

        MediaDAO mdAO = new MediaDAO(this.URL, this.dbName, this.dbPassword);
        mdAO.setURL(this.URL);
        mdAO.setDbName(this.dbName);
        mdAO.setDbPassword(this.dbPassword);

        mdAO.deleteGenreItemDetail(ID);
        
        session.removeAttribute("genreItemList");

        RequestDispatcher rd =
                request.getRequestDispatcher("/admin/genreSearch.jsp");

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

        Genre g = new Genre();
        g.setGenreID(Integer.parseInt(request.getParameter("genreID")));
        g.setGenreDescription(request.getParameter("genreDescription"));

        
        System.out.println("GenreID:" + g.getGenreID());
        System.out.println("GenreTypeDescription:" + g.getGenreDescription());
        
        mdAO.updateGenreItemDetail(g);
        
        session.removeAttribute("genreItemList");
        
        RequestDispatcher rd =
                request.getRequestDispatcher("/admin/genreSearch.jsp");

        rd.forward(request, response);
        
        return;
    }
    
    public void insertGenreItemDetail(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        HttpSession session = request.getSession(true);

        MediaDAO mdAO = new MediaDAO(this.URL, this.dbName, this.dbPassword);
        mdAO.setURL(this.URL);
        mdAO.setDbName(this.dbName);
        mdAO.setDbPassword(this.dbPassword);

        Genre g = new Genre();
                
        g.setGenreDescription(request.getParameter("genreDescription"));
        
        System.out.println("genreDescription =" + g.getGenreDescription());
        
        mdAO.insertGenreItemDetail(g);
        
        session.removeAttribute("genreItemList");
        
        RequestDispatcher rd =
                request.getRequestDispatcher("/admin/genreSearch.jsp");

        rd.forward(request, response);
        
        return;
    }
}
