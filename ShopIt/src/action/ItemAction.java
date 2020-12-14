package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.ItemManager;

public class ItemAction {

		public static void getPromoProduct(HttpServletRequest request) {
			request.setAttribute("promoProduct", ItemManager.getPromoItems());
		}
		public static void getProductById(int product_id, HttpServletRequest request, HttpServletResponse response) {
			request.setAttribute("itemProduct", ItemManager.getItemById(product_id));
		}
		
		public static void getItemById(int id, HttpServletRequest request, HttpServletResponse response) {
			request.setAttribute("item", ItemManager.getItemById(id));
		}
		
		public static void getItems(HttpServletRequest request, HttpServletResponse response) {
			request.setAttribute("itemCategory", ItemManager.getByCategory(CategoryAction.getSelectedCategory(request, response)));
		}
		public static void getAllProducts(HttpServletRequest request, HttpServletResponse response) {
			request.setAttribute("items",ItemManager.getAll());
		}
		public static void getSearchProducts(String recherche, HttpServletRequest request, HttpServletResponse response) {
			request.setAttribute("itemRecherche", ItemManager.geSearchProducts(recherche));
		}
		
	}


