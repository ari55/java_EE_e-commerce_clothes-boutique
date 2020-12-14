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
 * Servlet implementation class ListProduct
 */
@WebServlet("/listProduct")
public class ListProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListProduct() {
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
		ItemAction.getItems(request, response);
		ItemAction.getPromoProduct(request);
		CategoryAction.getAllCategories(request, response);
		ItemAction.getAllProducts(request, response);
		request.getRequestDispatcher("/WEB-INF/listproduct.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String strId = request.getParameter("itemId");
		String strQty = request.getParameter("qty");
		PanierAction.Ajouter(request, response, strId, strQty);
		doGet(request, response);
	}

}
