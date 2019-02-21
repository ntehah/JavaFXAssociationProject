package Project;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Consulter_Dep_Controller implements Initializable{
	
	@FXML
	private TableView<Depense>table;
	@FXML
	private TableColumn<Depense,Integer>id;
	@FXML
	private TableColumn<Depense,Integer>montant;
	@FXML
	private TableColumn<Depense,String>date;
	@FXML
	private TableColumn<Depense,String>objectif;
	@FXML
	private Label total;
	public ObservableList<Depense>data=  FXCollections.observableArrayList();
	public static Connection con = DAO.Connexion.getConnexion();
	public static PreparedStatement preparStat;
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
		float t=0;
		String requete ="SELECT * FROM TABLE_DEPENSE";
		preparStat=con.prepareStatement(requete);
		ResultSet rs = preparStat.executeQuery();
		while(rs.next()) {
			data.add(new Depense(rs.getInt("id"),rs.getFloat("MONTANT"),rs.getString("date_dep"),rs.getString("objectif")));
			table.setItems(data);
			id.setCellValueFactory(new PropertyValueFactory<>("id"));
			montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
			date.setCellValueFactory(new PropertyValueFactory<>("date"));
			objectif.setCellValueFactory(new PropertyValueFactory<>("objectif"));
			t=t+rs.getFloat("MONTANT");
				
		}
		total.setText(String.valueOf(t));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
