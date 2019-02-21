package Project;

import java.math.BigDecimal;


public class Revenue {
	private int id;
	private int cin;
	private float montant;
	private String date;
	private int num_cheque ;
	private String type;
	private String nom_banque;
	private BigDecimal num_compte;
	private String payment;
	
	
	
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public Revenue(int id, int cin, float montant, String date, int num_cheque, String type, String nom_banque,
			BigDecimal num_compte) {
		super();
		this.id = id;
		this.cin = cin;
		this.montant = montant;
		this.date = date;
		this.num_cheque = num_cheque;
		this.type = type;
		this.nom_banque = nom_banque;
		this.num_compte = num_compte;
		this.payment="nonpaye";
	}
	public Revenue() {
		super();
		this.payment="nonpaye";
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCin() {
		return cin;
	}
	public void setCin(int cin) {
		this.cin = cin;
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
	public int getNum_cheque() {
		return num_cheque;
	}
	public void setNum_cheque(int num_cheque) {
		this.num_cheque = num_cheque;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNom_banque() {
		return nom_banque;
	}
	public void setNom_banque(String nom_banque) {
		this.nom_banque = nom_banque;
	}
	public BigDecimal getNum_compte() {
		return num_compte;
	}
	public void setNum_compte(BigDecimal num_compte) {
		this.num_compte = num_compte;
	}
	
	
}