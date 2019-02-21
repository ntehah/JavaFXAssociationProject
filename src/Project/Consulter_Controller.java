package Project;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;

import javax.sql.rowset.Predicate;


import javafx.beans.value.ObservableValue;
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

public class Consulter_Controller implements Initializable {
	
	
@FXML
private TableView<Membre>table;
@FXML
private TableColumn<Membre,String>nom;
@FXML
private TableColumn<Membre,String>prenom;
@FXML
private TableColumn<Membre,Integer>cin;
@FXML
private TableColumn<Membre,String>date;
@FXML
private TableColumn<Membre,String>genre;
@FXML
private TableColumn<Membre,String>metier;
@FXML
private TableColumn<Membre,String>email;
@FXML
private TableColumn<Membre,String>adresse;
@FXML
private TableColumn<Membre,Integer>tel;
@FXML
private TableColumn<Membre,Integer>codePostal;
@FXML
private TextField search;

public ObservableList<Membre> data = FXCollections.observableArrayList();
public static Connection con = DAO.Connexion.getConnexion();
public static PreparedStatement preparStat;



FilteredList<Membre> filter = new FilteredList<Membre>(data,e->true);


@FXML
private void search ( KeyEvent event) {
	
	search.textProperty().addListener((observableValue,oldValue,newValue)->{
		filter.setPredicate((java.util.function.Predicate<? super Membre>)(Membre mbr)->{
			if(newValue==null||newValue.isEmpty()){
				return true;
			}
			String lowerCaseFilter=newValue.toLowerCase();
			if(mbr.getNom().toLowerCase().contains(lowerCaseFilter)){
				return true;
			}
			else if(mbr.getPrenom().toLowerCase().contains(lowerCaseFilter)){
				return true;
			}
			
			
			return false;
		});
	});
	SortedList<Membre> sortedData=new SortedList<>(filter);
	sortedData.comparatorProperty().bind(table.comparatorProperty());
	table.setItems(sortedData);
}




@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	
	try {
		
		String requete ="SELECT * FROM TABLE_MEMBRE";
		preparStat=con.prepareStatement(requete);
		ResultSet rs = preparStat.executeQuery();
		while(rs.next()) {
			data.add(new Membre(rs.getString("nom"),rs.getString("prenom"),rs.getString("date_birth")
					,rs.getInt("cin"),rs.getString("adress"),rs.getInt("tel"),rs.getString("email"),
					rs.getString("genre"),rs.getString("metier"),rs.getInt("code_postal")));
				table.setItems(data);
			nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
			prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
			date.setCellValueFactory(new PropertyValueFactory<>("date"));
			cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
			adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
			tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
			email.setCellValueFactory(new PropertyValueFactory<>("email"));
			genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
			metier.setCellValueFactory(new PropertyValueFactory<>("metier"));
			codePostal.setCellValueFactory(new PropertyValueFactory<>("codePostale"));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	
	
}

}



	
	
	
	
	

	
	


