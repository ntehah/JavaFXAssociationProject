package Project;

public class PersonneNec {
	
		   private String nom;
		   private String prenom;
		   private String date;
		   private int cin;
		   private String adresse;
		   private int tel;
		   private String profession;
		   private int nombreEnf;
		   private String genre;
		   private String etatCivil ;
		   private int codePostale ;
		   private float revenuAnnuelle;
		public float getRevenuAnnuelle() {
			return revenuAnnuelle;
		}
		public void setRevenuAnnuelle(float revenuAnnuelle) {
			this.revenuAnnuelle = revenuAnnuelle;
		}
		public PersonneNec() {
			super();
			
		}
		public PersonneNec(String nom, String prenom, String date, int cin, String adresse, int tel, String profession,
				int nombreEnf, String genre, String etatCivil, int codePostale, float rev) {
			super();
			this.nom = nom;
			this.prenom = prenom;
			this.date = date;
			this.cin = cin;
			this.adresse = adresse;
			this.tel = tel;
			this.profession = profession;
			this.nombreEnf = nombreEnf;
			this.genre = genre;
			
			this.etatCivil = etatCivil;
			this.codePostale = codePostale;
			revenuAnnuelle=rev;
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
		public String getProfession() {
			return profession;
		}
		public void setProfession(String profession) {
			this.profession = profession;
		}
		public int getNombreEnf() {
			return nombreEnf;
		}
		public void setNombreEnf(int nombreEnf) {
			this.nombreEnf = nombreEnf;
		}
		public String getGenre() {
			return genre;
		}
		public void setGenre(String genre) {
			this.genre = genre;
		}
		public String getEtatCivil() {
			return etatCivil;
		}
		public void setEtatCivil(String etatCivil) {
			this.etatCivil = etatCivil;
		}
		public int getCodePostale() {
			return codePostale;
		}
		public void setCodePostale(int codePostale) {
			this.codePostale = codePostale;
		}
			
			
			

	}

	
	
	

