package action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Item;
import entities.ItemPanier;
import entities.Panier;
import manager.ItemManager;
import manager.SessionManager;

public class PanierAction {
	public static Panier getCart(HttpServletRequest request) {
		HttpSession session = SessionManager.getSession(request);
		Panier cart = (Panier) session.getAttribute("cart");

		return cart;
	}
	// il faut que je cree l'objet panier dans la session

	public static void init(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = SessionManager.getSession(request);

		if (session.getAttribute("cart") == null) {
			Panier cart = new Panier();
			session.setAttribute("cart", cart);
		} else {
			Panier cart = (Panier) session.getAttribute("cart");

			for (ItemPanier itemC : cart.getCart().values()) {
				if (itemC.getSerialNumber() == null) {
					cart = new Panier();
					session.setAttribute("cart", cart);
					break;
				}
			}
		}
	}

	public static void EnleverDuPanier(String serial, HttpServletRequest request) {
		Panier cart = getCart(request);
		cart.RetirerUnItem(serial);
	}

	public static boolean isNumeric(String str) {
		try {
			@SuppressWarnings("unused")
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static void Ajouter(HttpServletRequest request, HttpServletResponse response, String strId, String strQty) {

		if ((PanierAction.isNumeric(strQty) || strQty.equals("")) && PanierAction.isNumeric(strId)) {
			// initialize la quantité a 1 s'il n'est pas initialisé

			int qty = 1;
			if (strQty != "")
				qty = Integer.parseInt(strQty);
			if (qty > 0) {
				int id = Integer.parseInt(strId);
				HttpSession session = SessionManager.getSession(request);
				Panier cart = (Panier) session.getAttribute("cart");

				Item item = ItemManager.getItemById(id);

				if (item != null) {
					ItemPanier itemPanier = null;
					if (cart.getCart().get(item.getSerialNumber()) != null) {
						itemPanier = cart.getCart().get(item.getSerialNumber());
						itemPanier.setQuantity(itemPanier.getQuantity() + qty);
					} else {
						itemPanier = new ItemPanier(item);
						itemPanier.setQuantity(qty);
					}
					cart.ajouterUnItem(itemPanier.getSerialNumber(), itemPanier);
				}
				session.setAttribute("cart", cart);
			} else {

			}
		} else {

		}
	}

	public static boolean validerQuantite(HttpServletRequest request) {
		boolean valid = true;
		HttpSession session = SessionManager.getSession(request);
		entities.Panier cart = (entities.Panier) session.getAttribute("cart");
		for (ItemPanier itemC : cart.getCart().values()) {
			String strQty = request.getParameter("qty-" + itemC.getSerialNumber());
			if (!PanierAction.isNumeric(strQty))
				valid = false;
			else if (Integer.parseInt(strQty) < 1) {
				valid = false;
			}
		}

		return valid;
	}

	public static void updateRemovedItem(HttpServletRequest request) {
		HttpSession session = SessionManager.getSession(request);
		Panier cart = (Panier) session.getAttribute("cart");
		Enumeration<String> postList = request.getParameterNames();
		// Vérifie si le bouton supprimer a été appuyer
		while (postList.hasMoreElements()) {
			String next = postList.nextElement();
			if (next.contains("p-del")) {
				String key = next.substring(6);
				cart.RetirerUnItem(key);
				System.out.println("Removing item: " + key);
			}
		}

	}

	public static void updateCart(HttpServletRequest request) {
		HttpSession session = SessionManager.getSession(request);
		entities.Panier cart = (entities.Panier) session.getAttribute("cart");
		for (ItemPanier itemPanier : cart.getCart().values()) {
			int qty = Integer.parseInt(request.getParameter("qty-" + itemPanier.getSerialNumber()));
			itemPanier.setQuantity(qty);
		}
	}

}
