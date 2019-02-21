package Project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GererRevenue_Controller implements Initializable{
	
	
	public void AjouterButtonAction(ActionEvent e) throws IOException {

		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("ajouter_Revenue.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
	public void ConsulterButtonAction(ActionEvent e) throws IOException {

		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("Consulter_Revenue.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	
	
	public void SupprimerButtonAction(ActionEvent e) throws IOException {

		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("Sup_Mod_revenue.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
	
	public void PayementButtonAction(ActionEvent e) throws IOException {

		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("Payement_Membre.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
