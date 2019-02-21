package Project;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.DAO_Depense;
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

public class Supprimer_Dep_controller implements Initializable {

	@FXML
	private TableView<Depense> table;
	@FXML
	private TableColumn<Depense, Integer> id;
	@FXML
	private TableColumn<Depense, Integer> montant;
	@FXML
	private TableColumn<Depense, String> date;
	@FXML
	private TableColumn<Depense, String> objectif;

	public static int id_Depense;

	public void ModifierButtonAction(ActionEvent e) throws IOException {

		if (table.getSelectionModel().getSelectedItem() != null) {

			Depense selectedrevenu = table.getSelectionModel().getSelectedItem();
			id_Depense = selectedrevenu.getId();

			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("modifier_depense.fxml"));

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}
		updateTable();
	}

	public ObservableList<Depense> data = FXCollections.observableArrayList();

	public  void updateTable() {

		for (int i = 0; i < table.getItems().size(); i++) {
			table.getItems().clear();
		}

		ResultSet rs = DAO_Depense.getAll();
		try {
			while (rs.next()) {
				data.add(new Depense(rs.getInt("id"),rs.getFloat("MONTANT"),rs.getString("date_dep"),rs.getString("objectif")));
				table.setItems(data);
				id.setCellValueFactory(new PropertyValueFactory<>("id"));
				montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
				date.setCellValueFactory(new PropertyValueFactory<>("date"));
				objectif.setCellValueFactory(new PropertyValueFactory<>("objectif"));
				
				

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void SupprimerButtonAction(ActionEvent e) throws IOException {

		if (table.getSelectionModel().getSelectedItem() != null) {
			Depense selectedDep = table.getSelectionModel().getSelectedItem();
			id_Depense = selectedDep.getId();
			 DAO_Depense.Supprimer(selectedDep.getId());
			updateTable();
		}
			
	}

	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		updateTable();

	}

}
