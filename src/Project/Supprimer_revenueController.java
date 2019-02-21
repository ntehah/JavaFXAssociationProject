package Project;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.DAO_Revenue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Supprimer_revenueController implements Initializable {


	@FXML
	private TableView<Revenue> table;
	@FXML
	private TableColumn<Revenue, Integer> id;
	@FXML
	private TableColumn<Revenue, Integer> cin;
	@FXML
	private TableColumn<Revenue, String> date;
	@FXML
	private TableColumn<Revenue, String> type;
	@FXML
	private TableColumn<Revenue, String> nom_banque;
	@FXML
	private TableColumn<Revenue, Integer> montant;
	@FXML
	private TableColumn<Revenue, Integer> num_compte;
	@FXML
	private TableColumn<Revenue, BigDecimal> num_cheque;


	public static int id_revenue;

	public void ModifierButtonAction(ActionEvent e) throws IOException {

		if (table.getSelectionModel().getSelectedItem() != null) {

			Revenue selectedrevenu = table.getSelectionModel().getSelectedItem();
			id_revenue = selectedrevenu.getId();

			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("modifier_Revenue.fxml"));

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}

	}

	public void SupprimerButtonAction(ActionEvent e) throws IOException {

		if (table.getSelectionModel().getSelectedItem() != null) {
			Revenue selectedrevenu = table.getSelectionModel().getSelectedItem();
			id_revenue = selectedrevenu.getId();
			int status = DAO_Revenue.Supprimer(selectedrevenu.getId());
			updateTable();
		}

	}

	public ObservableList<Revenue> data = FXCollections.observableArrayList();

	public void updateTable() {

		for (int i = 0; i < table.getItems().size(); i++) {
			table.getItems().clear();
		}

		ResultSet rs = DAO_Revenue.getAll();
		try {
			while (rs.next()) {
				data.add(new Revenue(rs.getInt("id"), rs.getInt("cin"), rs.getFloat("MONTANT"),
						rs.getString("date_rev"), rs.getInt("num_cheque"), rs.getString("type"),
						rs.getString("nom_banque"), rs.getBigDecimal("num_compte")));
				table.setItems(data);
				id.setCellValueFactory(new PropertyValueFactory<>("id"));
				cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
				montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
				date.setCellValueFactory(new PropertyValueFactory<>("date"));
				num_cheque.setCellValueFactory(new PropertyValueFactory<>("num_cheque"));
				type.setCellValueFactory(new PropertyValueFactory<>("type"));
				nom_banque.setCellValueFactory(new PropertyValueFactory<>("nom_banque"));
				num_compte.setCellValueFactory(new PropertyValueFactory<>("num_compte"));

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
