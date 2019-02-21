package Project;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

public  class Consulter_PerNec_Controller  implements Initializable {
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
	@FXML
	private TextField search;
	
	public ObservableList<PersonneNec>data=  FXCollections.observableArrayList();
	public static Connection con = DAO.Connexion.getConnexion();
	public static PreparedStatement preparStat;

	FilteredList<PersonneNec> filter = new FilteredList<PersonneNec>(data,e->true);
	
@FXML
private void search ( KeyEvent event) {

		
		search.textProperty().addListener((observableValue,oldValue,newValue)->{
			filter.setPredicate((java.util.function.Predicate<? super PersonneNec>)(PersonneNec per)->{
				if(newValue==null||newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter=newValue.toLowerCase();
				if(per.getNom().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				else if(per.getPrenom().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				
				
				return false;
			});
		});
		SortedList<PersonneNec> sortedData=new SortedList<>(filter);
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(sortedData);
	}
@Override
public void initialize(URL arg0, ResourceBundle arg1) {

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
		e.printStackTrace();
	}
}
}
