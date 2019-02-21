package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Project.Membre;
import Project.Revenue;




public class DAO_Membre {

	public static Connection conn = Connexion.getConnexion();
	public static PreparedStatement preparStat;
	public static ResultSet rs;

	public static int Ajouter(Membre mbr) {

		int st = 1;

		String Requete = "INSERT INTO  TABLE_MEMBRE(nom,prenom,cin,date_birth,email,adress,tel,genre,metier,code_postal) VALUES(?,?,?,?,?,?,?,?,?,?)";

		try {

			preparStat = conn.prepareStatement(Requete);

			preparStat.setString(1, mbr.getNom());
			preparStat.setString(2, mbr.getPrenom());
			preparStat.setInt(3, mbr.getCin());
			preparStat.setString(4, mbr.getDate());
			preparStat.setString(5, mbr.getEmail());
			preparStat.setString(6, mbr.getAdresse());
			preparStat.setInt(7, mbr.getTel());
			preparStat.setString(8, mbr.getGenre());
			preparStat.setString(9, mbr.getMetier());
			preparStat.setInt(10, mbr.getCodePostale());

			preparStat.executeQuery();

		} catch (SQLException ex) {
			ex.printStackTrace();
			st = 0;
			return st;

		}
		return st;
	}

	public static int Modifier(Membre mbr) {

		int ts =1 ;
		try {
			String Requete = "UPDATE TABLE_MEMBRE SET EMAIL =  ?,ADRESS = ?,TEL = ?,METIER = ?,CODE_POSTAL = ? WHERE CIN = ? ";

			preparStat = conn.prepareStatement(Requete);

			preparStat.setString(1, mbr.getEmail());
			preparStat.setString(2, mbr.getAdresse());
			preparStat.setInt(3, mbr.getTel());
			preparStat.setString(4, mbr.getMetier());
			preparStat.setInt(5, mbr.getCodePostale());
			preparStat.setInt(6, mbr.getCin());

			preparStat.execute();

		} catch (SQLException ex) {
			ex.printStackTrace();
			ts =0;
			return ts;

		}
		return ts;

	}
	
	public static ArrayList<Membre> getAll () {
		
		ArrayList<Membre> list=new ArrayList();
		
		String requete ="SELECT * FROM TABLE_MEMBRE";
		ResultSet rs,rs2 = null;
		try {
			preparStat=conn.prepareStatement(requete);
			 rs = preparStat.executeQuery();
			 
			 
			 
			 while (rs.next()) {
				 
				 Membre m =new Membre();
				 
				 int cin=rs.getInt("cin");
				 m.setCin(cin);
				 m.setNom(rs.getString("nom"));
				 m.setPrenom(rs.getString("prenom"));
				 m.setTel(rs.getInt("tel"));
				 m.setEmail(rs.getString("email"));
				 m.setMetier(rs.getString("metier"));
				
				 rs2=DAO_Revenue.getAll();
				 
				 while (rs2.next()) {
					
					 int cin2=rs2.getInt("cin");
					 
					 if (cin==cin2) {
						
						 Revenue r=new Revenue();
						 r.setDate(rs2.getString("date_rev"));
						 r.setMontant(rs2.getFloat("montant"));
						 r.setPayment("paye");
						 m.getList_Revenue().add(r);
						 
						 System.out.println(cin+ " "+rs2.getFloat("montant"));
					}
					
				}
				 
				list.add(m);
			}
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public static int Supprimer(int cin) {
		int st=1;
		
		 String Requete="DELETE FROM TABLE_MEMBRE WHERE CIN=?";
		 
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
	

	
	
	
	public static Membre getByCIN(int cin) {

		Membre mbr = new Membre();

		try {

			String Requete = "SELECT * FROM TABLE_MEMBRE WHERE cin=? ";
			preparStat = conn.prepareStatement(Requete);
			preparStat.setInt(1, cin);
			rs = preparStat.executeQuery();

			if (rs.next()) {

				mbr.setNom(rs.getString(1));
				mbr.setPrenom(rs.getString(2));
				mbr.setDate(rs.getString(10));
				mbr.setAdresse(rs.getString(5));
				mbr.setEmail(rs.getString(4));
				mbr.setTel(rs.getInt(6));
				mbr.setGenre(rs.getString(7));
				mbr.setMetier(rs.getString(8));
				mbr.setCodePostale(rs.getInt(9));

				return mbr;

			} else
				return null;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}

	}

}