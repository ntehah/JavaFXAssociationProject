package Project;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import DAO.DAO_Revenue;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Modifier_Rev_controller implements Initializable {

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
	private ImageView img_date;

	@FXML
	private TextField txt_cin;
	@FXML
	private TextField txt_montant;
	@FXML
	private DatePicker txt_date;
	@FXML
	private RadioButton btn_chq1;
	@FXML
	private RadioButton btn_liq1;
	@FXML
	private RadioButton btn_vir1;
	@FXML
	private TextField txt_numCompte;
	@FXML
	private TextField txt_numchq1;
	@FXML
	private TextField txt_nombanque1;
	@FXML
	private Label txt_type1;

	public void ModifierButtonAction(ActionEvent e) throws IOException {

		float montant = Float.parseFloat(txt_montant.getText());

		LocalDate date = LocalDate.now();
		String dateRevenue = date.toString();

		String nomBanque = txt_nombanque1.getText();
		int numchq = Integer.parseInt(txt_numchq1.getText());
		long numcompte = Integer.parseInt(txt_numCompte.getText());
		BigDecimal numCompte = BigDecimal.valueOf(numcompte);

		String type;
		if (btn_chq1.isSelected())
			type = "cheque";
		else if (btn_liq1.isSelected())
			type = "liquide";
		else
			type = "Virement";

		if (Valide()) {
			Revenue rev = DAO_Revenue.getByCIN(Supprimer_revenueController.id_revenue);

			rev.setMontant(montant);
			rev.setDate(dateRevenue);
			rev.setNom_banque(nomBanque);
			rev.setNum_cheque(numchq);
			rev.setNum_compte(numCompte);
			rev.setType(type);

			int status = DAO_Revenue.Modifier(rev);
			if (status > 0) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Modifier un revenue ");
				alert.setHeaderText("Information");
				alert.setContentText("Revenue est Modifier");
				alert.showAndWait();

			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Modification D'un Revenue");
				alert.setHeaderText("Information");
				alert.setContentText("Failed try again");
				alert.showAndWait();

			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Modification D'un Revenue");
			alert.setHeaderText("Information");
			alert.setContentText("Failed try again");
			alert.showAndWait();

		}

	}

	public void RechercheButtonAction(ActionEvent e) throws IOException {

		Revenue rev = DAO_Revenue.getByCIN(Supprimer_revenueController.id_revenue);
		if (rev == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Modification D'un Membre");
			alert.setHeaderText("CIN INTROUVABLE");
			alert.setContentText("Erreur!!!!!");
			alert.showAndWait();

		} else {

			txt_montant.setText(String.valueOf(rev.getMontant()));
			txt_numCompte.setText(String.valueOf(rev.getNum_compte()));
			txt_numchq1.setText(String.valueOf(rev.getNum_cheque()));
			txt_nombanque1.setText(rev.getNom_banque());

			if (rev.getType().equals("liquide"))
				btn_liq1.setSelected(true);
			else if (rev.getType().equals("cheque"))
				btn_chq1.setSelected(true);
			else
				btn_vir1.setSelected(true);

		}

	}

	public static Boolean montant_valide;
	public static Boolean numCompte_valide;
	public static Boolean numCheque_valide;
	public static Boolean nomBanque_valide;
	public static Boolean date_valide = true;

	public static boolean Valide() {
		if ((montant_valide == true) && (numCompte_valide == true) && (numCheque_valide == true)
				&& (nomBanque_valide == true))
			return true;

		return false;
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		Image image_error = new Image("/img/error.png");
		Image image_valide = new Image("/img/valide.png");

		Revenue rev = DAO_Revenue.getByCIN(Supprimer_revenueController.id_revenue);
		txt_montant.setText(String.valueOf(rev.getMontant()));
		txt_numCompte.setText(String.valueOf(rev.getNum_compte()));
		txt_numchq1.setText(String.valueOf(rev.getNum_cheque()));
		txt_nombanque1.setText(rev.getNom_banque());

		if (rev.getType().equals("liquide"))
			btn_liq1.setSelected(true);
		else if (rev.getType().equals("cheque"))
			btn_chq1.setSelected(true);
		else
			btn_vir1.setSelected(true);

		txt_montant.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String montant = txt_montant.getText();
				if (arg1)
					try {
						Integer.parseInt(montant);
						if (montant.matches("[0-9]*") == false) {
							img_montant.setImage(image_error);
							err_montant.setText("Montant  Invalid !!");
							montant_valide = false;
						} else if (txt_montant.getText().isEmpty()) {
							img_montant.setImage(image_error);
							montant_valide = false;

						} else {
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
						if (numCompte.matches("[0-9]*") && numCompte.length() != 10) {
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
		
		txt_numchq1.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String numCheque = txt_numchq1.getText();
				if (arg1)
					try {
						Integer.parseInt(numCheque);
						if (numCheque.matches("[0-9]*")==true && numCheque.length() != 6) {
							img_numCheque.setImage(image_error);
							err_numCheque.setText("Num Cheque Invalid !!");
							numCheque_valide = false;
						} else if (txt_numchq1.getText().isEmpty()) {
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
		
		txt_nombanque1.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String nomBanque = txt_nombanque1.getText();

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

	}
}
