package Project;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import DAO.DAO_Revenue;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ajouter_revenueController implements Initializable{
	
	
	@FXML
	private Label err_cin;
	@FXML
	private Label err_montant;
	@FXML
	private Label err_numCompte;
	@FXML
	private Label err_numCheque;
	@FXML
	private Label err_nomBanque;
	
	
	@FXML
	private ImageView img_cin;
	@FXML
	private ImageView img_montant;
	@FXML
	private ImageView img_numCompte;
	@FXML
	private ImageView img_numCheque;
	@FXML
	private ImageView img_nomBanque;
	
	
@FXML
private TextField txt_cin;
@FXML
private TextField txt_montant;
@FXML
private TextField txt_numCompte;
@FXML
private TextField txt_numCheque;
@FXML
private TextField txt_nomBanque;
@FXML
private RadioButton btn_L;
@FXML
private RadioButton btn_C;
@FXML
private RadioButton btn_v;
@FXML
private ToggleGroup type;

	public void AjoutButtonAction(ActionEvent e) throws IOException {
		
		String textcin= txt_cin.getText();
		int cin = Integer.parseInt(textcin);
		String textMontant = txt_montant.getText();
		int montant = Integer.parseInt(textMontant);
		
		
		
		String text_numcpt = txt_numCompte.getText();
		long num_cmpt = Integer.parseInt(text_numcpt);
		BigDecimal numCompte =BigDecimal.valueOf(num_cmpt);
		
		
		String text_numcheq = txt_numCheque.getText();
		int num_cheq = Integer.parseInt(text_numcheq);
		
		
		String type;
		LocalDate Date =LocalDate.now();
		String Date_rev =Date.toString();
		String nom_ban=txt_nomBanque.getText();
		
		if (btn_L.isSelected()) {
			type = "liquide";
		} else if (btn_C.isSelected()) {
			type = "cheque";
		} else {
			type = "virement bancaire";
		}
		if(Valide()) {
		Revenue rev =new Revenue();
		
		rev.setCin(cin);
		rev.setMontant(montant);
		rev.setNom_banque(nom_ban);
		rev.setNum_cheque(num_cheq);
		rev.setNum_compte(numCompte);
		rev.setType(type);
		rev.setDate(Date_rev);
		
		int status = DAO_Revenue.Ajouter(rev);
		if (status > 0) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("L'ajout D'un Revenue");
			alert.setHeaderText("Information");
			alert.setContentText("Revenue est ajouter");
			alert.showAndWait();

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("L'ajout D'un Revenue");
			alert.setHeaderText("Information");
			alert.setContentText("Failed try again");
			alert.showAndWait();

		}
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("L'ajout D'un Revenue");
			alert.setHeaderText("Information");
			alert.setContentText("Failed try again");
			alert.showAndWait();

		}
		

}
	
	public static Boolean cin_valide;
	public static Boolean montant_valide;
	public static Boolean numCompte_valide;
	public static Boolean numCheque_valide;
	public static Boolean nomBanque_valide;
	
	public static boolean Valide() {
		if ((cin_valide == true) && (montant_valide == true) &&  (numCompte_valide == true)
				&& (numCheque_valide == true) &&  (nomBanque_valide == true))
			return true;

		return false;
	}

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	
	Image image_error = new Image("/img/error.png");
	Image image_valide = new Image("/img/valide.png");
	
	txt_cin.focusedProperty().addListener(new ChangeListener<Boolean>() {

		@Override
		public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
			String textcin = txt_cin.getText();

			if (arg1)
				try {
					Integer.parseInt(textcin);
					if (textcin.length() != 8) {

						img_cin.setImage(image_error);
						err_cin.setText("Cin Invalid");
						cin_valide = false;

					}

					else if (txt_cin.getText().isEmpty()) {

						img_cin.setImage(image_error);
						cin_valide = false;

					}

					else {
						img_cin.setImage(image_valide);
						err_cin.setText("");
						cin_valide = true;
					}

				} catch (NumberFormatException e) {
					img_cin.setImage(image_error);
					cin_valide = false;
				}

		}

	});
	
	txt_montant.focusedProperty().addListener(new ChangeListener<Boolean>() {

		@Override
		public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
			String montant = txt_montant.getText();
			if (arg1)
				try {
					Integer.parseInt(montant);
					if (montant.matches("[0-9]*")==false) {
						img_montant.setImage(image_error);
						err_montant.setText("Montant  Invalid !!");
						montant_valide = false;
					} else if (txt_montant.getText().isEmpty()) {
						img_montant.setImage(image_error);
						montant_valide = false;
							
					}   
					else {
						img_montant.setImage(image_valide);
						err_montant.setText("");
						montant_valide = true;
					}

				} catch (Exception e) {
					img_montant.setImage(image_error);
					montant_valide = false;
				}

		}

	});
	

	
	txt_numCompte.focusedProperty().addListener(new ChangeListener<Boolean>() {

		@Override
		public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
			String numCompte = txt_numCompte.getText();
			if (arg1)
				try {
					Integer.parseInt(numCompte);
					
					if (numCompte.length() != 10) {
						img_numCompte.setImage(image_error);
						err_numCompte.setText("Num Compte  Invalid !!");
						numCompte_valide = false;
					} else if (txt_numCompte.getText().isEmpty()) {
						img_numCompte.setImage(image_error);
						numCompte_valide = false;
							
					}   
					else {
						img_numCompte.setImage(image_valide);
						err_numCompte.setText("");
						numCompte_valide = true;
					}

				} catch (Exception e) {
					img_numCompte.setImage(image_error);
					numCompte_valide = false;
				}

		}

	});
	
	txt_numCheque.focusedProperty().addListener(new ChangeListener<Boolean>() {

		@Override
		public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
			String numCheque = txt_numCheque.getText();
			if (arg1)
				try {
					Integer.parseInt(numCheque);
					if (numCheque.matches("[0-9]*")==true && numCheque.length() != 6) {
						img_numCheque.setImage(image_error);
						err_numCheque.setText("Num Cheque Invalid !!");
						numCheque_valide = false;
					} else if (txt_numCheque.getText().isEmpty()) {
						img_numCheque.setImage(image_error);
						numCheque_valide = false;
							
					}   
					else {
						img_numCheque.setImage(image_valide);
						err_numCheque.setText("");
						numCheque_valide = true;
					}

				} catch (Exception e) {
					img_numCheque.setImage(image_error);
					numCheque_valide = false;
				}

		}

	});
	
	txt_nomBanque.focusedProperty().addListener(new ChangeListener<Boolean>() {

		@Override
		public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
			String nomBanque = txt_nomBanque.getText();

			if (arg1) {
				try {

					if (nomBanque.matches("[A-Za-z ]*") == false) {
						img_nomBanque.setImage(image_error);
						err_nomBanque.setText("Essayer avec chaine de caractere ");
						nomBanque_valide = false;
					} else if (nomBanque.matches("") == true) {
						img_nomBanque.setImage(image_error);
						nomBanque_valide = false;
					} else {
						img_nomBanque.setImage(image_valide);
						err_nomBanque.setText("");
						nomBanque_valide = true;
					}

				} catch (Exception e) {
					nomBanque_valide = false;
				}

			}
		}

	});

	
	
	
	
	
	
	
	
	
	/*type.selectedToggleProperty().addListener( new ChangeListener<Toggle>()) {
		
		@Override
		public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
			// TODO Auto-generated method stub
			
		}
	});
	if(btn_L.isSelected())
		txt_numCheque.setVisible(false);
		txt_nomBanque.setVisible(false);
		txt_numCompte.setVisible(false);
	*/
}



}

