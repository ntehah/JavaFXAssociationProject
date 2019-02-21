package Project;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

import DAO.DAO_Membre;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ajouter_Controller implements Initializable {

	@FXML
	private Label err_nom;
	@FXML
	private Label err_prenom;
	@FXML
	private Label err_cin;
	@FXML
	private Label err_date;
	@FXML
	private Label err_add;
	@FXML
	private Label err_tel;
	@FXML
	private Label err_email;

	@FXML
	private ImageView img_nom;
	@FXML
	private ImageView img_prenom;
	@FXML
	private ImageView img_cin;
	@FXML
	private ImageView img_date;
	@FXML
	private ImageView img_add;
	@FXML
	private ImageView img_code;
	@FXML
	private ImageView img_tel;
	@FXML
	private ImageView img_email;
	@FXML
	private ImageView img_genre;
	@FXML
	private ImageView img_metier;

	@FXML
	private TextField txt_nom;
	@FXML
	private TextField txt_prenom;
	@FXML
	private TextField txt_cin;
	@FXML
	private TextField txt_adress;
	@FXML
	private TextField txt_tel;
	@FXML
	private TextField txt_email;
	@FXML
	private DatePicker txt_date;
	@FXML
	private TextField txt_codepostal;
	@FXML
	private RadioButton radio_H;
	@FXML
	private RadioButton radio_F;
	@FXML
	private RadioButton radio_A;
	@FXML
	private RadioButton radio_C;
	@FXML
	private RadioButton radio_K;

	public void AjoutButtonAction(ActionEvent e) throws IOException {

		LocalDate Datebirth = txt_date.getValue();

		String nom = txt_nom.getText();
		String prenom = txt_prenom.getText();
		String textcin = txt_cin.getText();
		String adress = txt_adress.getText();
		String email = txt_email.getText();
		String textTel = txt_tel.getText();
		String textcodep = txt_codepostal.getText();
		String genre;
		String metier;
		if (radio_H.isSelected()) {
			genre = "Homme";
		} else {
			genre = "Femme";
		}
		if (radio_A.isSelected()) {
			metier = "A";
		} else if (radio_C.isSelected()) {
			metier = "Chomeur";
		} else {
			metier = "K";
		}

		int CIN = Integer.parseInt(textcin);
		int tele = Integer.parseInt(textTel);
		int codep = Integer.parseInt(textcodep);
		String DateB = Datebirth.toString();
		if (Valide() == true) {
			Membre test = DAO_Membre.getByCIN(CIN);
			if (test != null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("L'ajout D'un Membre");
				alert.setHeaderText("Membre Exist");
				alert.setContentText("Essayer avec un nouveau Membre ");
				alert.showAndWait();

			} else {

				Membre mbr = new Membre();

				mbr.setNom(nom);
				mbr.setPrenom(prenom);
				mbr.setCin(CIN);
				mbr.setAdresse(adress);
				mbr.setEmail(email);
				mbr.setTel(tele);
				mbr.setDate(DateB);
				mbr.setGenre(genre);
				mbr.setMetier(metier);
				mbr.setCodePostale(codep);
				int status = DAO_Membre.Ajouter(mbr);
				if (status > 0) {

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("L'ajout D'un Membre");
					alert.setHeaderText("Information");
					alert.setContentText("Membre est ajouter");
					alert.showAndWait();

				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("L'ajout D'un Membre");
					alert.setHeaderText("Information");
					alert.setContentText("Failed try again");
					alert.showAndWait();

				}
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("L'ajout D'un Membre");
			alert.setHeaderText("Les Champs ");
			alert.setContentText("Erreur verifier les champs !!! ");
			alert.showAndWait();
		}
	}

	public static Boolean cin_valide;
	public static Boolean nom_valide;
	public static Boolean prenom_valide;
	public static Boolean adress_valide;
	public static Boolean email_valide;
	public static Boolean code_valide;
	public static Boolean tel_valide;
	public static Boolean date_valide=true;

	public static boolean Valide() {
		if ((cin_valide == true) && (nom_valide == true) && (prenom_valide == true) && (email_valide == true)
				&& (tel_valide == true) && (date_valide == true) && (code_valide == true))
			return true;

		return false;
	}

	public static int calculateAge(LocalDate birthDate) {
		LocalDate today = LocalDate.now();
		if ((birthDate != null)) {
			return Period.between(birthDate, today).getYears();
		} else {
			return 0;
		}
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
		txt_nom.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String nom = txt_nom.getText();

				if (arg1) {
					try {

						if (nom.matches("[A-Za-z]*") == false) {
							img_nom.setImage(image_error);
							err_nom.setText("Essayer avec chaine de caractere et sans espace");
							nom_valide = false;
						} else if (nom.matches("") == true) {
							img_nom.setImage(image_error);
							nom_valide = false;
						} else {
							img_nom.setImage(image_valide);
							err_nom.setText("");
							nom_valide = true;
						}

					} catch (Exception e) {
						nom_valide = false;
					}

				}
			}

		});

		txt_prenom.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String prenom = txt_prenom.getText();
				if (arg1)
					try {

						if (prenom.matches("[A-Za-z]*") == false) {
							img_prenom.setImage(image_error);
							err_prenom.setText("Essayer avec chaine de caractere et sans espace");
							prenom_valide = false;
						} else if (prenom.matches("") == true) {
							img_prenom.setImage(image_error);
							prenom_valide = false;
						} else {
							img_prenom.setImage(image_valide);
							err_prenom.setText("");
							prenom_valide = true;
						}

					} catch (Exception e) {
						prenom_valide = false;
					}

			}

		});

		txt_adress.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String adress = txt_adress.getText();
				if (arg1)
					try {

						if (adress.matches("[A-Za-z0-9 ]*") == false) {
							img_add.setImage(image_error);
							err_add.setText("Essayer avec chaine de caractere");
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

		txt_date.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				LocalDate date = txt_date.getValue();
				int age = calculateAge(date);
				if (arg1)
					try {

						if (txt_date.getValue()==null) {
							img_date.setImage(image_error);
							date_valide = false;
							err_date.setText("");
						} 
						else if(age <= 18) {
							img_date.setImage(image_error);
							err_date.setText("age doit etre superieur a 18 !!!");
							date_valide = false;
							
							}
						else {
							img_date.setImage(image_valide);
							err_date.setText("");
							date_valide = true;
						}

					} catch (Exception e) {
						img_date.setImage(image_error);
						date_valide = false;
					}

			}

		});
		radio_H.setSelected(true);
		radio_A.setSelected(true);

	}

}
