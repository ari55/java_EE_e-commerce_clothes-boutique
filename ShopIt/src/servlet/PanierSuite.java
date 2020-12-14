package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager.SessionManager;

/**
 * Servlet implementation class PanierSuite
 */
@WebServlet("/panierSuite")
public class PanierSuite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PanierSuite() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		if (request.getParameter("fromCart") != null) {
			HttpSession session = SessionManager.getSession(request);
			if (session.getAttribute("user") == null) {
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/WEB-INF/panierSuite.jsp").forward(request, response);
			}
		}
	}

}
