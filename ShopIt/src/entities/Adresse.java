package entities;

public class Adresse {
	int id;
	String no,appartement,rue,zip,ville,etat,pays;
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNo() {
		return no;
	}



	public void setNo(String no) {
		this.no = no;
	}



	public String getAppartement() {
		return appartement;
	}



	public void setAppartement(String appartement) {
		this.appartement = appartement;
	}



	public String getRue() {
		return rue;
	}



	public void setRue(String rue) {
		this.rue = rue;
	}



	public String getZip() {
		return zip;
	}



	public void setZip(String zip) {
		this.zip = zip;
	}



	public String getVille() {
		return ville;
	}



	public void setVille(String ville) {
		this.ville = ville;
	}



	public String getEtat() {
		return etat;
	}



	public void setEtat(String etat) {
		this.etat = etat;
	}



	public String getPays() {
		return pays;
	}



	public void setPays(String pays) {
		this.pays = pays;
	}



	public Adresse() {
		super();
	}



	public Adresse(int id, String no, String appartement, String rue, String zip, String ville, String etat,
			String pays) {
		super();
		this.id = id;
		this.no = no;
		this.appartement = appartement;
		this.rue = rue;
		this.zip = zip;
		this.ville = ville;
		this.etat = etat;
		this.pays = pays;
	}
	
	
}
