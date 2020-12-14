package entities;

public class Category {

	private int id;
	private String name, description;
	private int order;

	
	public Category() {
		super();
	}
	public Category(int id, String name, String description, int order) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.order = order;
	}





	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getOrder() {
		return order;
	}


	public void setOrder(int order) {
		this.order = order;
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
	
	
	
}
