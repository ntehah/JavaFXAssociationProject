package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Project.Evennement;
import Project.PersonneNec;

public class DAO_PersonneNec {
	
	public static Connection conn = Connexion.getConnexion();
	public static PreparedStatement preparStat;
	public static ResultSet rs;
	
public static int Ajouter(PersonneNec per) {
		
		int st = 1;

	String Requete = "INSERT INTO  NECESSITEUSE(nom,prenom,cin,date_per,profession,adress,tel,genre,enfantnbr,codepostal,etatcivil,revenue) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

	try {

		preparStat = conn.prepareStatement(Requete);

		preparStat.setString(1, per.getNom());
		preparStat.setString(2, per.getPrenom());
		preparStat.setInt(3, per.getCin());
		preparStat.setString(4, per.getDate());
		preparStat.setString(5, per.getProfession());
		preparStat.setString(6, per.getAdresse());
		preparStat.setInt(7, per.getTel());
		preparStat.setString(8, per.getGenre());
		preparStat.setInt(9, per.getNombreEnf());
		preparStat.setInt(10, per.getCodePostale());
		preparStat.setString(11, per.getEtatCivil());
		preparStat.setFloat(12, per.getRevenuAnnuelle());

		preparStat.executeQuery();

	} catch (SQLException ex) {
		ex.printStackTrace();
		st = 0;
		return st;

	}
	return st;
}

public static int Modifier(PersonneNec per) {

	int ts =1 ;
	try {
		String Requete = "UPDATE NECESSITEUSE SET nom =  ?,prenom = ?,date_per = ?,profession = ?,adress = ?,tel =?,genre = ?,enfantnbr = ?,codepostal = ?,etatcivil = ?,revenue = ? WHERE cin = ? ";

		preparStat = conn.prepareStatement(Requete);

		preparStat.setString(1, per.getNom());
		preparStat.setString(2, per.getPrenom());
		preparStat.setInt(12, per.getCin());
		preparStat.setString(3, per.getDate());
		preparStat.setString(4, per.getProfession());
		preparStat.setString(5, per.getAdresse());
		preparStat.setInt(6, per.getTel());
		preparStat.setString(7, per.getGenre());
		preparStat.setInt(8, per.getNombreEnf());
		preparStat.setInt(9, per.getCodePostale());
		preparStat.setString(10, per.getEtatCivil());
		preparStat.setFloat(11, per.getRevenuAnnuelle());
		

		preparStat.execute();

	} catch (SQLException ex) {
		ex.printStackTrace();
		ts =0;
		return ts;

	}
	return ts;

}

	public static int Supprimer(int cin) {
		int st=1;
		
		 String Requete="DELETE FROM NECESSITEUSE WHERE CIN=?";
		 
	        try {
			   
	              preparStat=conn.prepareStatement(Requete);
	              preparStat.setInt(1, cin);
	              preparStat.execute();
	   
	           } catch (SQLException ex) {
	        	   ex.printStackTrace();
	        	   st=0;
	        	   return st;
	        	      }
	        return st;
	        

	}
	
	
	
	public static PersonneNec getByCIN(int cin) {

		
		PersonneNec per = new PersonneNec();
		try {

			String Requete = "SELECT * FROM NECESSITEUSE WHERE cin=? ";
			preparStat = conn.prepareStatement(Requete);
			preparStat.setInt(1, cin);
			rs = preparStat.executeQuery();

			if (rs.next()) {
				
				per.setNom(rs.getString(1));
				per.setPrenom(rs.getString(8));
				per.setDate(rs.getString(2));
				per.setAdresse(rs.getString(5));
				per.setProfession(rs.getString(4));
				per.setTel(rs.getInt(10));
				per.setGenre(rs.getString(7));
				per.setNombreEnf(rs.getInt(6));
				per.setEtatCivil(rs.getString(9));
				per.setCodePostale(rs.getInt(11));
				per.setRevenuAnnuelle(rs.getFloat(12));

				return per;

			} else
				return null;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}

	}
	

}


