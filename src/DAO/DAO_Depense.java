package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Project.Depense;
import Project.Membre;
import Project.Revenue;

public class DAO_Depense {
	public static Connection conn = Connexion.getConnexion();
	public static PreparedStatement preparStat;
	public static ResultSet rs;
	
public static int Ajouter(Depense dep) {
		int st=1;
		
		

		String Requete = "INSERT INTO TABLE_DEPENSE(id,objectif,montant,date_dep) VALUES(?,?,?,?)";

		try {

			preparStat = conn.prepareStatement(Requete);

			preparStat.setInt(1,dep.getId());
			preparStat.setString(2, dep.getObjectif());
			preparStat.setFloat(3, dep.getMontant());
			preparStat.setString(4, dep.getDate());
			preparStat.executeQuery();

		} catch (SQLException ex) {
			ex.printStackTrace();
			st = 0;
			return st;

		}
		return st;
	}
public static int Supprimer(int id) {
	int st=1;
	
	 String Requete="DELETE FROM TABLE_Depense WHERE id=?";
	 
        try {
		   
              preparStat=conn.prepareStatement(Requete);
              preparStat.setInt(1,id);
              preparStat.execute();
   
           } catch (SQLException ex) {
        	   ex.printStackTrace();
        	   st=0;
        	   return st;
        	      }
        return st;
        

}
public static int Modifier(Depense  dep) {

	int ts =1 ;
	try {
		String Requete = "UPDATE TABLE_DEPENSE SET Montant =  ?,Objectif = ?,Date_dep = ? WHERE id= ? ";

		preparStat = conn.prepareStatement(Requete);

		preparStat.setFloat(1, dep.getMontant());
		preparStat.setString(2, dep.getObjectif());
		preparStat.setString(3, dep.getDate());
		preparStat.setInt(4, dep.getId());
		
		

		preparStat.execute();

	} catch (SQLException ex) {
		ex.printStackTrace();
		ts =0;
		return ts;

	}
	return ts;

}
public static ResultSet getAll () {
	String requete ="SELECT * FROM TABLE_DEPENSE";
	ResultSet rs = null;
	try {
		preparStat=conn.prepareStatement(requete);
		 rs = preparStat.executeQuery();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return rs;
}
public static Depense getByid(int id) {

Depense dep = new Depense();

	try {

		String Requete = "SELECT * FROM TABLE_DEPENSE WHERE id=? ";
		preparStat = conn.prepareStatement(Requete);
		preparStat.setInt(1, id);
		rs = preparStat.executeQuery();

		if (rs.next()) {

			dep.setMontant(rs.getFloat(2));
			dep.setObjectif(rs.getString(4));
			dep.setDate(rs.getString(3));
			
			return dep;

		} else
			return null;

	} catch (SQLException ex) {
		ex.printStackTrace();
		return null;
	}

}


		   

	
}