package Project;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import DAO.DAO_PersonneNec;
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



public class Supprimer_PerNece_Controller implements Initializable {
	
	@FXML
	private TableView<PersonneNec>table;
	@FXML
	private TableColumn<PersonneNec,String>nom;
	@FXML
	private TableColumn<PersonneNec,String>prenom;
	@FXML
	private TableColumn<PersonneNec,String>date;
	@FXML
	private TableColumn<PersonneNec,Integer>cin;
	@FXML
	private TableColumn<PersonneNec,String>adresse;
	@FXML
	private TableColumn<PersonneNec,Integer>tel;
	@FXML
	private TableColumn<PersonneNec,String>profession;
	@FXML
	private TableColumn<PersonneNec,String>nombreEnf;
	@FXML
	private TableColumn<PersonneNec,String>genre;
	@FXML
	private TableColumn<PersonneNec,String>etatCivil;
	@FXML
	private TableColumn<PersonneNec,Integer>codePostale;
	@FXML
	private TableColumn<PersonneNec,Float>revenuAnnuelle;
	
	public static int cin_p;
	public static Connection con = DAO.Connexion.getConnexion();
	public static PreparedStatement preparStat;
	
	public ObservableList<PersonneNec> data = FXCollections.observableArrayList();
	
	public void SupprimerButtonAction(ActionEvent e) throws IOException {

		if (table.getSelectionModel().getSelectedItem() != null) {
			PersonneNec selectedP = table.getSelectionModel().getSelectedItem();
			cin_p = selectedP.getCin();
			DAO_PersonneNec.Supprimer(selectedP.getCin());
			updateTable();
		}

	}
	
	public void ModifierButtonAction(ActionEvent e) throws IOException {

		if (table.getSelectionModel().getSelectedItem() != null) {

			PersonneNec selectedP = table.getSelectionModel().getSelectedItem();
			cin_p = selectedP.getCin();

			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("Modifier_PersonneNec.fxml"));

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}
		updateTable();
	}
	
	public void updateTable() {

		for (int i = 0; i < table.getItems().size(); i++) {
			table.getItems().clear();
		}

		try {
			String requete ="SELECT * FROM NECESSITEUSE";
			preparStat=con.prepareStatement(requete);
			ResultSet rs = preparStat.executeQuery();
			while(rs.next()) {
				data.add(new PersonneNec(rs.getString("NOM"),rs.getString("PRENOM"),rs.getString("DATE_PER"),rs.getInt("CIN")
						,rs.getString("ADRESS"),rs.getInt("TEL"),rs.getString("PROFESSION"),rs.getInt("ENFANTNBR"),rs.getString("GENRE"),rs.getString("ETATCIVIL"),rs.getInt("CODEPOSTAL"),rs.getFloat("REVENUE")));
				table.setItems(data);
				nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
				prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
				date.setCellValueFactory(new PropertyValueFactory<>("date"));
				cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
				adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
				tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
				profession.setCellValueFactory(new PropertyValueFactory<>("profession"));
				nombreEnf.setCellValueFactory(new PropertyValueFactory<>("nombreEnf"));
				genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
				etatCivil.setCellValueFactory(new PropertyValueFactory<>("etatCivil"));
				codePostale.setCellValueFactory(new PropertyValueFactory<>("codePostale"));
				revenuAnnuelle.setCellValueFactory(new PropertyValueFactory<>("revenuAnnuelle"));
				
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
