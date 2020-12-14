package servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CategoryAction;
import action.ItemAction;
import action.LoginAction;
import manager.CookieManager;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
		manageConnection(request, response);

		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		/*
		 * User user = new User(); if (request.getParameter("btn_login") != null) {
		 * String username = request.getParameter("email"); String password =
		 * request.getParameter("password"); user.setEmail(username);
		 * user.setPassword(password); LoginManager loginManager=new LoginManager();
		 * String authorize=loginManager.userTotest(user);
		 * if(authorize.equals("SUCCESS LOGIN")) { HttpSession
		 * session=request.getSession(); session.setAttribute("login",user.getEmail());
		 * RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/index.jsp");
		 * rd.forward(request, response); } else {
		 * request.setAttribute("WrongLoginMsg",authorize); RequestDispatcher
		 * rd=request.getRequestDispatcher("/WEB-INF/login.jsp"); rd.include(request,
		 * response); } }
		 */

		manageConnection(request, response);
		LoginAction.connexion(request, response);

		@SuppressWarnings("unchecked")
		String loginState = (String) ((HashMap<String, String>) request.getAttribute("cleValeurErreur")).get("loginState");
		System.out.println(loginState);

		if (loginState.equals("ok"))
			if (request.getParameter("fromCart") == null)
				response.sendRedirect("home");
			else
				request.getRequestDispatcher("/WEB-INF/contact.jsp").forward(request, response);
		else
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	private void manageConnection(HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("user") != null) {
			request.getSession().removeAttribute("user");
			request.setAttribute("logout", "ok");
			CookieManager.destroy("id", request, response);
			CookieManager.destroy("token", request, response);
		}
	}

}
