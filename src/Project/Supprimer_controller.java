package Project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DAO.DAO_Membre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;




public class Supprimer_controller implements Initializable{
	
	@FXML
	private TextField txt_cin;
	@FXML
	private Label lb_nom;
	@FXML
	private Label lb_prenom;
	@FXML
	private Label lb_date;
	@FXML
	private Label lb_adress;
	@FXML
	private Label lb_codepostal;
	@FXML
	private Label lb_email;
	@FXML
	private Label lb_tel;
	@FXML
	private Label lb_metier;
	
	
	public void SupprimerButtonAction(ActionEvent e) throws IOException {
		
		String CIN = txt_cin.getText();
		int cin = Integer.parseInt(CIN);

		if(DAO_Membre.getByCIN(cin)!=null) {
			int status=DAO_Membre.Supprimer(cin);
			if (status > 0) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Supprimer Membre");
				alert.setHeaderText("Information");
				alert.setContentText("Membre est Supprimer");
				alert.showAndWait();

			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Supprimer un Membre");
				alert.setHeaderText("Information");
				alert.setContentText("Failed try again");
				alert.showAndWait();

			}
			
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Chercher un Membre");
			alert.setHeaderText("CIN INTROUVABLE");
			alert.setContentText("Erreur!!!!!");
			alert.showAndWait();
			
		}
		
	}
		
		
	
	public void RechercheButtonAction(ActionEvent e) throws IOException {
		
		String CIN = txt_cin.getText();
		int cin = Integer.parseInt(CIN);

		Membre mbr = DAO_Membre.getByCIN(cin);
		if (mbr == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Chercher un Membre");
			alert.setHeaderText("CIN INTROUVABLE");
			alert.setContentText("Erreur!!!!!");
			alert.showAndWait();

		} else {
			lb_nom.setText(mbr.getNom());
			lb_prenom.setText(mbr.getPrenom());
			lb_date.setText(mbr.getDate());
			lb_adress.setText(mbr.getAdresse());
			lb_codepostal.setText(String.valueOf(mbr.getCodePostale()));
			lb_email.setText(mbr.getEmail());
			lb_tel.setText(String.valueOf(mbr.getTel()));
			lb_metier.setText(mbr.getMetier());
			
			

		}
		
	}
		
		

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
