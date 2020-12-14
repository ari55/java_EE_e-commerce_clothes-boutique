package entities;

import util.Const;

public class Item {
	private int id,category,stock;
	private String name,description,serialNumber,image;
	private double price;
	
	
	public Item() {
		super();
	}


	public Item(int id, int category, int stock, String name, String description, String serialNumber, String image,
			double price) {
		super();
		this.id = id;
		this.category = category;
		this.stock = stock;
		this.name = name;
		this.description = description;
		this.serialNumber = serialNumber;
		this.image = image;
		this.price = price;
	}

	public String getImgAndPath(){
		return Const.IMAGE_PRODUCT + image;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getCategory() {
		return category;
	}


	public void setCategory(int category) {
		this.category = category;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getSerialNumber() {
		return serialNumber;
	}


	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
