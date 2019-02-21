package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Project.Depense;
import Project.Evennement;
import Project.Membre;

public class DAO_Evennement {
	public static Connection conn = Connexion.getConnexion();
	public static PreparedStatement preparStat;
	public static ResultSet rs;
	
	public static int Ajouter(Evennement even) {

		int ts =1 ;
		try {
			String Requete = "INSERT INTO  EVENEMENT(nom,duree,revenue,depense,lieu,type,cotisation,date_ev) VALUES(?,?,?,?,?,?,?,?)";

			preparStat = conn.prepareStatement(Requete);

			preparStat.setString(1, even.getNom());
			preparStat.setInt(2, even.getDuree());
			preparStat.setFloat(3,even.getRevenue());
			preparStat.setFloat(4,even.getDepense());
			preparStat.setString(5,even.getLieu());
			preparStat.setString(6,even.getType());
			preparStat.setFloat(7,even.getCotisation());
			preparStat.setString(8, even.getDate());
			
			

			preparStat.execute();

		} catch (SQLException ex) {
			ex.printStackTrace();
			ts =0;
			return ts;

		}
		return ts;

	}
	
	
	public static int Modifier(Evennement even) {

		int ts =1 ;
		try {
			String Requete = "UPDATE EVENEMENT SET nom =  ?,duree = ?,revenue = ?,depense = ?,type = ?,cotisation =?,lieu = ? WHERE id = ? ";

			preparStat = conn.prepareStatement(Requete);

			preparStat.setString(1, even.getNom());
			preparStat.setInt(2, even.getDuree());
			preparStat.setFloat(3,even.getRevenue());
			preparStat.setFloat(4,even.getDepense());
			preparStat.setString(7,even.getLieu());
			preparStat.setString(5,even.getType());
			preparStat.setFloat(6,even.getCotisation());
			preparStat.setInt(8,even.getId());
			

			preparStat.execute();

		} catch (SQLException ex) {
			ex.printStackTrace();
			ts =0;
			return ts;

		}
		return ts;

	}
	
	public static int Supprimer(int id) {
		int st=1;
		
		 String Requete="DELETE FROM EVENEMENT WHERE id=?";
		 
	        try {
			   
	              preparStat=conn.prepareStatement(Requete);
	              preparStat.setInt(1, id);
	              preparStat.execute();
	   
	           } catch (SQLException ex) {
	        	   ex.printStackTrace();
	        	   st=0;
	        	   return st;
	        	      }
	        return st;
	        

	}
	
	public static Evennement getByid(int id) {

		Evennement even = new Evennement ();

			try {

				String Requete = "SELECT * FROM EVENEMENT WHERE id=? ";
				preparStat = conn.prepareStatement(Requete);
				preparStat.setInt(1, id);
				rs = preparStat.executeQuery();

				if (rs.next()) {

					even.setNom(rs.getString(2));
					even.setDuree(rs.getInt(3));
					even.setRevenue(rs.getFloat(4));
					even.setDepense(rs.getFloat(5));
					even.setLieu(rs.getString(6));
					even.setType(rs.getString(7));
					even.setCotisation(rs.getFloat(8));
					
					
					return even;

				} else
					return null;

			} catch (SQLException ex) {
				ex.printStackTrace();
				return null;
			}

		}


}
