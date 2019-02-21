package Project;

public class Depense {
	private int id;
	private float montant;
	private String date;
	private String objectif ;
	public Depense(int id, float montant, String date, String objectif) {
		super();
		this.id = id;
		this.montant = montant;
		this.date = date;
		this.objectif = objectif;
	}
	public Depense() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getObjectif() {
		return objectif;
	}
	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}
	

}
