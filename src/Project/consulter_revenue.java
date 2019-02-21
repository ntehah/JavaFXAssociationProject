
package Project;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;

import DAO.Connexion;
import DAO.DAO_Membre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class consulter_revenue implements Initializable {
	
	
@FXML
private TableView<Revenue>table;
@FXML
private TableColumn<Revenue,Integer>id;
@FXML
private TableColumn<Revenue,Integer>cin;
@FXML
private TableColumn<Revenue,String>date;
@FXML
private TableColumn<Revenue,String>type;
@FXML
private TableColumn<Revenue,String>nom_banque;
@FXML
private TableColumn<Revenue,Integer>montant;
@FXML
private TableColumn<Revenue,Integer>num_compte;
@FXML
private TableColumn<Revenue,BigDecimal>num_cheque;
@FXML
private Label total;

public ObservableList<Revenue>data=  FXCollections.observableArrayList();
public static Connection con = DAO.Connexion.getConnexion();
public static PreparedStatement preparStat;

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	try {
		float t=0;
		String requete ="SELECT * FROM TABLE_REVENUE";
		preparStat=con.prepareStatement(requete);
		ResultSet rs = preparStat.executeQuery();
		while(rs.next()) {
			data.add(new Revenue(rs.getInt("id"),rs.getInt("cin"),rs.getFloat("MONTANT"),rs.getString("date_rev")
					,rs.getInt("num_cheque"),rs.getString("type"),rs.getString("nom_banque"),rs.getBigDecimal("num_compte")));
				table.setItems(data);
			id.setCellValueFactory(new PropertyValueFactory<>("id"));
			cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
			montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
			date.setCellValueFactory(new PropertyValueFactory<>("date"));
			num_cheque.setCellValueFactory(new PropertyValueFactory<>("num_cheque"));
			type.setCellValueFactory(new PropertyValueFactory<>("type"));
			nom_banque.setCellValueFactory(new PropertyValueFactory<>("nom_banque"));
			num_compte.setCellValueFactory(new PropertyValueFactory<>("num_compte"));
			t=t+rs.getFloat("MONTANT");
			
		}
		total.setText(String.valueOf(t));
	} catch (SQLException e) {
		e.printStackTrace();
	}
	

	
}

}
