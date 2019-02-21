package Project;

import java.util.ArrayList;

public class Membre {
	 String nom;
	String prenom;
	String date;
	int cin;
	String adresse;
	int tel;
	String email;
	String genre;
	String metier;
	int codePostale ;
	ArrayList<Revenue> list_Revenue;
	
	public Membre(String nom, String prenom, String date, int cin, String adresse, int tel, String email, String genre,
			String metier, int codePostale) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.date = date;
		this.cin = cin;
		this.adresse = adresse;
		this.tel = tel;
		this.email = email;
		this.genre = genre;
		this.metier = metier;
		this.codePostale = codePostale;
		
		list_Revenue=new ArrayList();
	}
	public Membre(String nom, String prenom, int cin, int tel, String email, String metier) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.tel = tel;
		this.email = email;
		this.metier = metier;
		list_Revenue=new ArrayList();
	}
	public Membre() {
		super();
		list_Revenue=new ArrayList();
		// TODO Auto-generated constructor stub
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getCin() {
		return cin;
	}
	public void setCin(int cin) {
		this.cin = cin;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
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
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getMetier() {
		return metier;
	}
	public void setMetier(String metier) {
		this.metier = metier;
	}
	public int getCodePostale() {
		return codePostale;
	}
	public void setCodePostale(int codePostale) {
		this.codePostale = codePostale;
	}
	public ArrayList<Revenue> getList_Revenue() {
		return list_Revenue;
	}
	public void setList_Revenue(ArrayList<Revenue> list_Revenue) {
		this.list_Revenue = list_Revenue;
	}

	
}