package entities;

public class User {
	int id;
	String lastName, firstName, email, password;
	Adresse shipAddress;
	int idPrivilege;
	
	public User() {
		super();
	}
	

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}


	public User(int id, String lastName, String firstName, String email, String password,
			Adresse shipAddress) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.password = password;
		this.shipAddress = shipAddress;
	}

	
	public User(int id, String lastName, String firstName, String email, String password, Adresse shipAddress,
			int idPrivilege) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.password = password;
		this.shipAddress = shipAddress;
		this.idPrivilege = idPrivilege;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Adresse getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(Adresse shipAddress) {
		this.shipAddress = shipAddress;
	}

	public int getIdPrivilege() {
		return idPrivilege;
	}

	public void setIdPrivilege(int idPrivilege) {
		this.idPrivilege = idPrivilege;
	}


	
}
