package Project;

public class Evennement {
	private int id;
	private String nom;
	private int Duree;
	private float revenue;
	private float depense;
	private String lieu;
	private String type;
	private float cotisation;
	private String date;
	public Evennement(int id, String nom, int duree, float revenue, float depense, String lieu, String type,
			float cotisation, String date) {
		super();
		this.id = id;
		this.nom = nom;
		Duree = duree;
		this.revenue = revenue;
		this.depense = depense;
		this.lieu = lieu;
		this.type = type;
		this.cotisation = cotisation;
		this.date = date;
	}
	public Evennement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getDuree() {
		return Duree;
	}
	public void setDuree(int duree) {
		Duree = duree;
	}
	public float getRevenue() {
		return revenue;
	}
	public void setRevenue(float revenue) {
		this.revenue = revenue;
	}
	public float getDepense() {
		return depense;
	}
	public void setDepense(float depense) {
		this.depense = depense;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getCotisation() {
		return cotisation;
	}
	public void setCotisation(float cotisation) {
		this.cotisation = cotisation;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}