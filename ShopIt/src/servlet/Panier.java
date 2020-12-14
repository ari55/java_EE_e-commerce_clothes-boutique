package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CategoryAction;
import action.ItemAction;
import action.PanierAction;

/**
 * Servlet implementation class Panier
 */
@WebServlet("/panier")
public class Panier extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Panier() {
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
		ItemAction.getPromoProduct(request);
		CategoryAction.getAllCategories(request, response);
		request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("removeItem") != null) {

			String serial = request.getParameter("removeItem");
			PanierAction.EnleverDuPanier(serial, request);
			;
		}
		
		PanierAction.updateRemovedItem(request);
		boolean qtyValide = PanierAction.validerQuantite(request);
		// TODO Afficher msg d'erreur pour quantité invalid
		// Si checkout rediriger vers la facture
		if (request.getParameter("checkout") != null && qtyValide) {

			// Met à jour les quantités
			PanierAction.updateCart(request);

			// Rediriger
			request.getRequestDispatcher("WEB-INF/panierSuite.jsp").forward(request, response);
		} else if (request.getParameter("back") != null && qtyValide) { // Met a jour les quantité et redirge sur index

			// Met à jour les quantités
			PanierAction.updateCart(request);

			// Rediriger
			response.sendRedirect("home");
		} else if (request.getParameter("refresh") != null && qtyValide) { // Met a jour les quantité et demeure sur la
																			// page

			// Met à jour les quantités
			PanierAction.updateCart(request);

			// Rediriger
			response.sendRedirect("panier");
		} else
			doGet(request, response);
	}

}
