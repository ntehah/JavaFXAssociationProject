package Project;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.DAO_Evennement;
import DAO.DAO_Revenue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Supprimer_eve_Controller implements Initializable{
	
	@FXML
	private TableView<Evennement>table;
	@FXML
	private TableColumn<Evennement,Integer>id;
	@FXML
	private TableColumn<Evennement,String>nom;
	@FXML
	private TableColumn<Evennement,String>Duree;
	@FXML
	private TableColumn<Evennement,String>date;
	@FXML
	private TableColumn<Evennement,String>lieu;
	@FXML
	private TableColumn<Evennement,String>type;
	@FXML
	private TableColumn<Evennement,Float>cotisation;
	@FXML
	private TableColumn<Evennement,Float>revenue;
	@FXML
	private TableColumn<Evennement,Float>depense;
	
	public static int id_event;
	public static Connection con = DAO.Connexion.getConnexion();
	public static PreparedStatement preparStat;

	public void ModifierButtonAction(ActionEvent e) throws IOException {

		if (table.getSelectionModel().getSelectedItem() != null) {

			Evennement selectedEv = table.getSelectionModel().getSelectedItem();
			id_event = selectedEv.getId();

			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("Modifier_Evennement.fxml"));

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}
		updateTable();
	}

	public void SupprimerButtonAction(ActionEvent e) throws IOException {

		if (table.getSelectionModel().getSelectedItem() != null) {
			Evennement selectedEv = table.getSelectionModel().getSelectedItem();
			id_event = selectedEv.getId();
			int status = DAO_Evennement.Supprimer(selectedEv.getId());
			updateTable();
		}

	}
	
	public ObservableList<Evennement> data = FXCollections.observableArrayList();

	public void updateTable() {

		for (int i = 0; i < table.getItems().size(); i++) {
			table.getItems().clear();
		}

		try {
			String requete ="SELECT * FROM EVENEMENT";
			preparStat=con.prepareStatement(requete);
			ResultSet rs = preparStat.executeQuery();
			while(rs.next()) {
				data.add(new Evennement(rs.getInt("id"),rs.getString("nom"),rs.getInt("DUREE"),rs.getFloat("REVENUE")
						,rs.getFloat("DEPENSE"),rs.getString("LIEU"),rs.getString("TYPE"),rs.getFloat("COTISATION"),rs.getString("DATE_EV")));
					table.setItems(data);
				id.setCellValueFactory(new PropertyValueFactory<>("id"));
				nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
				Duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
				revenue.setCellValueFactory(new PropertyValueFactory<>("revenue"));
				depense.setCellValueFactory(new PropertyValueFactory<>("depense"));
				date.setCellValueFactory(new PropertyValueFactory<>("date"));
				lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
				type.setCellValueFactory(new PropertyValueFactory<>("type"));
				cotisation.setCellValueFactory(new PropertyValueFactory<>("cotisation"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		updateTable();
		
	}
	

}
