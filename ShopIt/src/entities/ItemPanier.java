package entities;

import java.io.Serializable;

public class ItemPanier extends Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int quantity;

	public ItemPanier() {
		super();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	
	
	public ItemPanier(Item item) {
		setId(item.getId());
		setCategory(item.getCategory());
		setStock(item.getStock());
		setName(item.getName());
		setDescription(item.getDescription());
		setSerialNumber(item.getSerialNumber());
		setImage(item.getImage());
		setPrice(item.getPrice());
		quantity = 0;

	}

	public boolean inStock() {
		return super.getStock() >= quantity;
	}

}
