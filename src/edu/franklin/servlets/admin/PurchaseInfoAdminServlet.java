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
import edu.franklin.beans.PurchaseInfo;
import edu.franklin.models.MediaDAO;

@WebServlet(name="purchaseInfoAdmin", urlPatterns = {"/admin/purchaseInfoAdmin"})
public class PurchaseInfoAdminServlet extends HttpServlet {

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

        switch(action) {
            case "getAllPurchaseInfo":
                this.getAllPurchaseInfo(request, response);
                break;
            case "getPurchaseInfo":
                this.getPurchaseInfo(request, response);
                break;
            case "deletePurchaseInfo":
            	this.deletePurchaseInfo(request, response);
            	break;
            case "updatePurchaseInfo":
            	this.updatePurchaseInfo(request, response);
            	break;
            case "insertPurchaseInfo":
            	this.insertPurchaseInfo(request, response);
            	break;
            default:
                RequestDispatcher rd =
                request.getRequestDispatcher("/error-404.jsp");

                rd.forward(request, response);
                break;
        }
    }
    
    public void getAllPurchaseInfo(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        HttpSession session = request.getSession(true);

        String purchaseInfoItemName = request.getParameter("purchaseInfoItemName");

        MediaDAO mdAO = new MediaDAO(this.URL, this.dbName, this.dbPassword);
        mdAO.setURL(this.URL);
        mdAO.setDbName(this.dbName);
        mdAO.setDbPassword(this.dbPassword);


        session.setAttribute("purchaseInfoList", mdAO.returnAllPurchaseInfo(purchaseInfoItemName));

        RequestDispatcher rd =
                request.getRequestDispatcher("/admin/purchaseInfoListDisplay.jsp");

        rd.forward(request, response);
    }
    
    public void getPurchaseInfo(HttpServletRequest request,
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

        session.setAttribute("purchaseInfoList", mdAO.returnPurchaseInfo(ID));
        
        System.out.println("HELLO WORLD");

        RequestDispatcher rd =
                request.getRequestDispatcher("/admin/purchaseInfoUpdate.jsp");

        System.out.println("HELLO WORLD 2");

        rd.forward(request, response);
    }
    

    public void deletePurchaseInfo(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

    	System.out.println("in deletePurchaseInfo");
    	
        HttpSession session = request.getSession(true);

        int purchaseID = Integer.parseInt(request.getParameter("purchaseID"));

        System.out.println("purchaseID:" + purchaseID);

        MediaDAO mdAO = new MediaDAO(this.URL, this.dbName, this.dbPassword);
        mdAO.setURL(this.URL);
        mdAO.setDbName(this.dbName);
        mdAO.setDbPassword(this.dbPassword);

        //TODO - ALREADY WORKING???
        mdAO.deletePurchaseInfo(purchaseID);

        RequestDispatcher rd =
                request.getRequestDispatcher("/admin/purchaseInfoSearch.jsp");

        rd.forward(request, response);
        
        return;
    }
    
    public void updatePurchaseInfo(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        HttpSession session = request.getSession(true);

        MediaDAO mdAO = new MediaDAO(this.URL, this.dbName, this.dbPassword);
        mdAO.setURL(this.URL);
        mdAO.setDbName(this.dbName);
        mdAO.setDbPassword(this.dbPassword);

        PurchaseInfo pi = new PurchaseInfo();
        pi.setPurchaseID(Integer.parseInt(request.getParameter("purchaseID")));
        pi.setPurchaseLocation(request.getParameter("purchaseLocation"));
        
        System.out.println("ID:" + pi.getPurchaseID());
        System.out.println("name:" + pi.getPurchaseLocation());
        
        mdAO.updatePurchaseInfo(pi);
        
        RequestDispatcher rd =
                request.getRequestDispatcher("/admin/purchaseInfoSearch.jsp");

        rd.forward(request, response);
        
        return;
    }
    
    public void insertPurchaseInfo(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        HttpSession session = request.getSession(true);

        MediaDAO mdAO = new MediaDAO(this.URL, this.dbName, this.dbPassword);
        mdAO.setURL(this.URL);
        mdAO.setDbName(this.dbName);
        mdAO.setDbPassword(this.dbPassword);

        PurchaseInfo pi = new PurchaseInfo();
        pi.setPurchaseLocation(request.getParameter("purchaseLocation"));
        
        System.out.println("ID:" + pi.getPurchaseID());
        System.out.println("name:" + pi.getPurchaseLocation());
        
        mdAO.insertPurchaseInfo(pi);
        
        RequestDispatcher rd =
                request.getRequestDispatcher("/admin/purchaseInfoSearch.jsp");

        rd.forward(request, response);
        
        return;
    }
}
