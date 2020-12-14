package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CategoryAction;
import action.InscriptionAction;
import action.ItemAction;


/**
 * Servlet implementation class Inscription
 */
@WebServlet("/inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Inscription() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		ItemAction.getPromoProduct(request);
		CategoryAction.getAllCategories(request, response);
		request.getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		System.out.println(InscriptionAction.addUser(request, response));
		if (InscriptionAction.addUser(request, response))
			 if (request.getParameter("fromCart") == null)
			  request.getRequestDispatcher("/WEB-INF/loginComplete.jsp").forward(request,
			  response); else
			  request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,
			  response);
			 
			 else
			request.getRequestDispatcher("/WEB-INF/loginComplete.jsp").forward(request, response);
	}

}
