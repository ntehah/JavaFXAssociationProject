package Project;

public class Parametre {
	
	public String nom;
	public int tel;
	public String email;
	public int id;
	public int rib;
	public Parametre(String nom, int tel, String email, int id, int rib) {
		super();
		this.nom = nom;
		this.tel = tel;
		this.email = email;
		this.id = id;
		this.rib = rib;
	}
	public Parametre() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRib() {
		return rib;
	}
	public void setRib(int rib) {
		this.rib = rib;
	}
	
	

}
