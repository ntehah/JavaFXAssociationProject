package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.Connexion;
import Project.Revenue;


public class DAO_Revenue {
		public static Connection conn = Connexion.getConnexion();
		public static PreparedStatement preparStat;
		public static ResultSet rs;

		public static int Ajouter(Revenue rev) {

			int st = 1;

			String Requete = "INSERT INTO  TABLE_REVENUE(id,cin, montant,num_cheque, type, nom_banque,date_rev,num_compte) VALUES(?,?,?,?,?,?,?,?)";

			try {

				preparStat = conn.prepareStatement(Requete);
				preparStat.setInt(1, rev.getId());
				preparStat.setInt(2, rev.getCin());
				preparStat.setFloat(3, rev.getMontant());
				preparStat.setLong(4, rev.getNum_cheque());
				preparStat.setString(5, rev.getType());
				preparStat.setString(6, rev.getNom_banque());
				preparStat.setString(7, rev.getDate());
				preparStat.setBigDecimal(8, rev.getNum_compte());
				
				preparStat.executeQuery();

			} catch (SQLException ex) {
				ex.printStackTrace();
				st = 0;
				return st;

			}
			return st;
		}

		public static int Modifier(Revenue rev) {

			int ts =1 ;
			try {
				String Requete = "UPDATE TABLE_REVENUE SET montant= ?,type = ?,num_cheque = ?,nom_banque= ?,date_rev = ?,num_compte = ? WHERE id = ? ";

				preparStat = conn.prepareStatement(Requete);

				preparStat.setFloat(1, rev.getMontant());
				preparStat.setString(2, rev.getType());
				preparStat.setLong(3, rev.getNum_cheque());
				preparStat.setString(4, rev.getNom_banque());
				preparStat.setString(5, rev.getDate());
				preparStat.setBigDecimal(6, rev.getNum_compte());
				preparStat.setInt(7, rev.getId());
			
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
			
			 String Requete="DELETE FROM TABLE_REVENUE WHERE id=?";
			 
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
public static ResultSet getAll () {
	String requete ="SELECT * FROM TABLE_REVENUE";
	ResultSet rs = null;
	try {
		preparStat=conn.prepareStatement(requete);
		 rs = preparStat.executeQuery();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return rs;
}
		public static Revenue getByCIN(int id) {

				Revenue rev = new Revenue();

				try {

					String Requete = "SELECT * FROM TABLE_REVENUE WHERE id=? ";
					preparStat = conn.prepareStatement(Requete);
					preparStat.setInt(1, id);
					rs = preparStat.executeQuery();

					if (rs.next()) {

						rev.setMontant(rs.getFloat(1));
						rev.setNum_cheque(rs.getInt(3));
						rev.setType(rs.getString(2));
						rev.setNom_banque(rs.getString(4));
						rev.setDate(rs.getString(5));
						rev.setNum_compte(rs.getBigDecimal(7));
						
						return rev;
					} 
					else
						return null;

				} catch (SQLException ex) {
					ex.printStackTrace();
					return null;
				}

		}
}

