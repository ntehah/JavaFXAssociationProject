package Project;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class Consulter_evenenment implements Initializable {
	
	
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
@FXML
private TextField search;



public ObservableList<Evennement>data=  FXCollections.observableArrayList();
public static Connection con = DAO.Connexion.getConnexion();
public static PreparedStatement preparStat;

FilteredList<Evennement> filter = new FilteredList<Evennement>(data,e->true);


@FXML
private void search ( KeyEvent event) {
	
	search.textProperty().addListener((observableValue,oldValue,newValue)->{
		filter.setPredicate((java.util.function.Predicate<? super Evennement>)(Evennement ev)->{
			if(newValue==null||newValue.isEmpty()){
				return true;
			}
			String lowerCaseFilter=newValue.toLowerCase();
			if(ev.getType().toLowerCase().contains(lowerCaseFilter)){
				return true;
			}
			
			
			
			return false;
		});
	});
	SortedList<Evennement> sortedData=new SortedList<>(filter);
	sortedData.comparatorProperty().bind(table.comparatorProperty());
	table.setItems(sortedData);
}

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
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
		e.printStackTrace();
	}
}
}

	



  

