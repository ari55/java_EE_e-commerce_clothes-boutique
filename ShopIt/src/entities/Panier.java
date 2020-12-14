package entities;

import java.io.Serializable;
import java.util.HashMap;

public class Panier implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, ItemPanier> cart;

	public Panier(HashMap<String, ItemPanier> cart) {
		super();
		this.cart = cart;
	}

	public Panier() {
		cart = new HashMap<String, ItemPanier>();
	}

	public HashMap<String, ItemPanier> getCart() {
		return cart;
	}

	public void setCart(HashMap<String, ItemPanier> cart) {
		this.cart = cart;
	}

	public void ajouterUnItem(String cle, ItemPanier itemPanier) {
		cart.put(cle, itemPanier);
	}

	public void RetirerUnItem(String cle) {
		cart.remove(cle);
	}

	public boolean isEmpty() {
		return cart.size() == 0;
	}

	public double generateSubTotal() {
		double subTotal = 0;
		for (ItemPanier itemPanier : cart.values())
			subTotal += itemPanier.getPrice() * itemPanier.getQuantity();

		return subTotal;
	}

	public double generateTotal() {
		return generateSubTotal();
	}

	public int getSize() {
		return cart.size();
	}
}
