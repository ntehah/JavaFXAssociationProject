package Project;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.DAO_Membre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class Payement_Membre implements Initializable {

	@FXML
	private TableView<Membre> table;
	@FXML
	private TableView<Revenue> tablerevenue;
	@FXML
	private TableColumn<Membre, String> nom;
	@FXML
	private TableColumn<Membre, String> prenom;
	@FXML
	private TableColumn<Membre, Integer> cin;
	@FXML
	private TableColumn<Membre, String> metier;
	@FXML
	private TableColumn<Membre, String> email;
	@FXML
	private TableColumn<Membre, Integer> tel;
	@FXML
	private TableColumn<Revenue, Float> montant;
	@FXML
	private TableColumn<Revenue, String> date;
	@FXML
	private TableColumn<Revenue, String> payement;
	
	@FXML
	private TextField search;

	public ObservableList<Membre> data = FXCollections.observableArrayList();
	public ObservableList<Revenue> data2 = FXCollections.observableArrayList();
	public static Connection con = DAO.Connexion.getConnexion();
	public static PreparedStatement preparStat;
	public static PreparedStatement preparStat2;

	FilteredList<Membre> filter = new FilteredList<Membre>(data, e -> true);

	private void search(KeyEvent event) {

		search.textProperty().addListener((observableValue, oldValue, newValue) -> {
			filter.setPredicate((java.util.function.Predicate<? super Membre>) (Membre mbr) -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (mbr.getNom().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (mbr.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}

				return false;
			});
		});
		SortedList<Membre> sortedData = new SortedList<>(filter);
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(sortedData);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {

		
			
			ArrayList< Membre> list=DAO_Membre.getAll();
			
			for (int i = 0; i < list.size(); i++) {
				
				Membre m =list.get(i);
				

				for (int j = 0; j < m.getList_Revenue().size(); j++) {
					
					data.add(m);
					
					table.setItems(data);
					nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
					prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
					cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
					tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
					email.setCellValueFactory(new PropertyValueFactory<>("email"));
					metier.setCellValueFactory(new PropertyValueFactory<>("metier"));
					
					Revenue revenue=m.getList_Revenue().get(j);
					data2.add(revenue);
					tablerevenue.setItems(data2);
					montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
					date.setCellValueFactory(new PropertyValueFactory<>("date"));
					payement.setCellValueFactory(new PropertyValueFactory<>("payment"));
				}
				
				if (m.getList_Revenue().size()==0) {
					
					
					data.add(m);
					
					table.setItems(data);
					nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
					prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
					cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
					tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
					email.setCellValueFactory(new PropertyValueFactory<>("email"));
					metier.setCellValueFactory(new PropertyValueFactory<>("metier"));
					
					Revenue revenue=new Revenue();
					data2.add(revenue);
					tablerevenue.setItems(data2);
					montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
					date.setCellValueFactory(new PropertyValueFactory<>("date"));
					payement.setCellValueFactory(new PropertyValueFactory<>("payment"));
					
				}
				
			}


			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
