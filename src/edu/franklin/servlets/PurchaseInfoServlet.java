package edu.franklin.servlets;

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

import edu.franklin.models.MediaDAO;

@WebServlet(name = "purchaseInfo", urlPatterns = { "/purchaseInfo" })
public class PurchaseInfoServlet extends HttpServlet {

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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		System.out.println("doGet:" + action);

		switch (action) {
		case "getItemCounts":
			this.getItemCounts(request, response);
			break;
		default:
			RequestDispatcher rd = request
					.getRequestDispatcher("/error-404.jsp");

			rd.forward(request, response);
			break;
		}

	}

	public void getItemCounts(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		System.out.println("doGet Hello:");

		MediaDAO mdAO = new MediaDAO(this.URL, this.dbName, this.dbPassword);

		session.setAttribute("itemCountList", mdAO.returnPurchaseInfoItemsCounts());

		RequestDispatcher rd = request
				.getRequestDispatcher("/purchaseInfoListDisplay.jsp");

		rd.forward(request, response);
	}
}
