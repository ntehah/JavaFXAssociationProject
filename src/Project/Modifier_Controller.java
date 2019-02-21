package Project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DAO.DAO_Membre;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Modifier_Controller implements Initializable {

	@FXML
	private ImageView img_add;
	@FXML
	private ImageView img_code;
	@FXML
	private ImageView img_tel;
	@FXML
	private ImageView img_email;

	@FXML
	private Label err_add;
	@FXML
	private Label err_tel;
	@FXML
	private Label err_email;
	@FXML
	private Label err_code;

	@FXML
	private TextField txt_cin;
	@FXML
	private TextField txt_adress;
	@FXML
	private TextField txt_tel;
	@FXML
	private TextField txt_email;
	@FXML
	private TextField txt_codepostal;
	@FXML
	private RadioButton btn_A;
	@FXML
	private RadioButton btn_C;
	@FXML
	private RadioButton btn_K;
	@FXML
	private Label lb_nom;
	@FXML
	private Label lb_prenom;
	@FXML
	private Label lb_date;
	@FXML
	private Label lb_genre;

	public void ModifierButtonAction(ActionEvent e) throws IOException {

		String adress = txt_adress.getText();
		String email = txt_email.getText();
		int tel = Integer.parseInt(txt_tel.getText());
		int codepostal = Integer.parseInt(txt_codepostal.getText());
		String CIN = txt_cin.getText();
		int cin = Integer.parseInt(CIN);

		Membre mbr = DAO_Membre.getByCIN(cin);

		mbr.setAdresse(adress);
		mbr.setEmail(email);
		mbr.setTel(tel);
		mbr.setCodePostale(codepostal);
		mbr.setCin(cin);
		if(Valide()) {
		int status = DAO_Membre.Modifier(mbr);
		if (status > 0) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Modifier un Membre");
			alert.setHeaderText("Information");
			alert.setContentText("Membre est Modifier");
			alert.showAndWait();

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Modification D'un Membre");
			alert.setHeaderText("Information");
			alert.setContentText("Failed try again");
			alert.showAndWait();

		}
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Modification D'un Membre");
			alert.setHeaderText("Les Champs ");
			alert.setContentText("Erreur verifier les champs !!! ");
			alert.showAndWait();
			
		}

	}

	public void RechercheButtonAction(ActionEvent e) throws IOException {

		String CIN = txt_cin.getText();
		int cin = Integer.parseInt(CIN);

		Membre mbr = DAO_Membre.getByCIN(cin);
		if (mbr == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Modification D'un Membre");
			alert.setHeaderText("CIN INTROUVABLE");
			alert.setContentText("Erreur!!!!!");
			alert.showAndWait();

		} else {
			lb_nom.setText(mbr.getNom());
			lb_prenom.setText(mbr.getPrenom());
			lb_date.setText(mbr.getDate());
			lb_genre.setText(mbr.getGenre());

			txt_adress.setText(mbr.getAdresse());
			txt_tel.setText(String.valueOf(mbr.getTel()));
			txt_email.setText(mbr.getEmail());
			txt_codepostal.setText(String.valueOf(mbr.getCodePostale()));

		}

	}

	public static Boolean adress_valide=true;
	public static Boolean email_valide=true;
	public static Boolean code_valide=true;
	public static Boolean tel_valide=true;

	public static boolean Valide() {
		if ((email_valide == true) && (adress_valide == true) && (tel_valide == true) && (code_valide == true))
			return true;

		return false;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Image image_error = new Image("/img/error.png");
		Image image_valide = new Image("/img/valide.png");

		txt_adress.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String adress = txt_adress.getText();
				if (arg1)
					try {

						if (adress.matches("[A-Za-z0-9 ]*") == false) {
							img_add.setImage(image_error);
							err_add.setText("Adresse Invalid !!!");
							adress_valide = false;
						} else if (adress.matches("") == true) {
							img_add.setImage(image_error);
							adress_valide = false;
						} else {
							img_add.setImage(image_valide);
							err_add.setText("");
							adress_valide = true;
						}

					} catch (Exception e) {
						adress_valide = false;
					}

			}

		});
		
		txt_codepostal.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String code = txt_codepostal.getText();
				if (arg1)
					try {
						Integer.parseInt(code);
						if (code.length() <= 4) {
							img_code.setImage(image_error);
							err_code.setText("CodePostal Invalid !!!");
							code_valide = false;
						} else if (txt_codepostal.getText().isEmpty()) {
							img_code.setImage(image_error);
							code_valide = false;
						} else {
							img_code.setImage(image_valide);
							code_valide = true;
						}

					} catch (Exception e) {
						img_code.setImage(image_error);
						code_valide = false;
					}

			}

		});
		
		txt_tel.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String tel = txt_tel.getText();
				if (arg1)
					try {
						Integer.parseInt(tel);
						if (tel.matches("[0-9]*") && tel.length() != 8) {
							img_tel.setImage(image_error);
							err_tel.setText("Telephone  Invalid !!");
							tel_valide = false;
						} else if (txt_tel.getText().isEmpty()) {
							img_tel.setImage(image_error);
							tel_valide = false;

						} else {
							img_tel.setImage(image_valide);
							err_tel.setText("");
							tel_valide = true;
						}

					} catch (Exception e) {
						img_tel.setImage(image_error);
						tel_valide = false;
					}

			}

		});
		
		
		txt_email.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String email = txt_email.getText();
				String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\"
						+ ".[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

				if (arg1)
					try {
						java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
						java.util.regex.Matcher m = p.matcher(email);
						if (m.matches() == false) {
							img_email.setImage(image_error);
							err_email.setText("Email Invalid !!");
							email_valide = false;
							if (txt_email.getText().isEmpty())
								err_email.setText("");
						} else {
							img_email.setImage(image_valide);
							err_email.setText("");
							email_valide = true;
						}

					} catch (Exception e) {
						img_email.setImage(image_error);
						email_valide = false;
					}

			}

		});
		btn_A.setSelected(true);
	}

}
