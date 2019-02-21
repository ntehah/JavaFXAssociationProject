package Project;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import DAO.DAO_Evennement;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ajouter_EvenementController implements Initializable {

	@FXML
	private TextField txt_nom;
	@FXML
	private TextField txt_duree;
	@FXML
	private TextField txt_revenue;
	@FXML
	private TextField txt_lieu;
	@FXML
	private TextField txt_type;
	@FXML
	private TextField txt_cotisation;
	@FXML
	private TextField txt_depense;

	@FXML
	private Label lb_nom;
	@FXML
	private Label lb_duree;
	@FXML
	private Label lb_revenue;
	@FXML
	private Label lb_depense;
	@FXML
	private Label lb_lieu;
	@FXML
	private Label lb_type;
	@FXML
	private Label lb_cotisation;
	@FXML
	private ImageView img_nom;
	@FXML
	private ImageView img_duree;
	@FXML
	private ImageView img_revenue;
	@FXML
	private ImageView img_depense;
	@FXML
	private ImageView img_lieu;
	@FXML
	private ImageView img_type;
	@FXML
	private ImageView img_cotisation;

	public void AjouterButtonAction(ActionEvent e) throws IOException {

		if (Valide()) {
			String nom = txt_nom.getText();
			int duree = Integer.parseInt(txt_duree.getText());
			float revenue = Integer.parseInt(txt_revenue.getText());
			float depense = Integer.parseInt(txt_depense.getText());
			String lieu = txt_lieu.getText();
			String type = txt_type.getText();
			float cotisation = Integer.parseInt(txt_cotisation.getText());
			LocalDate date = LocalDate.now();
			String date_e = date.toString();
			Evennement ev = new Evennement();
			ev.setNom(nom);
			ev.setDuree(duree);
			ev.setRevenue(revenue);
			ev.setDepense(depense);
			ev.setLieu(lieu);
			ev.setType(type);
			ev.setCotisation(cotisation);
			ev.setDate(date_e);

			int status = DAO_Evennement.Ajouter(ev);

			if (status > 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("L'ajout D'un Evenement");
				alert.setHeaderText("Information");
				alert.setContentText("Membre est ajouter");
				alert.showAndWait();

			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("L'ajout D'un Evenement");
				alert.setHeaderText("Information");
				alert.setContentText("Failed try again");
				alert.showAndWait();

			}

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("L'ajout D'un Evenement");
			alert.setHeaderText("Les Champs ");
			alert.setContentText("Erreur verifier les champs !!! ");
			alert.showAndWait();
		}

	}

	public static Boolean nom_valide;
	public static Boolean duree_valide;
	public static Boolean revenue_valide;
	public static Boolean depense_valide;
	public static Boolean lieu_valide;
	public static Boolean type_valide;
	public static Boolean cotisation_valide;

	public static boolean Valide() {
		if ((nom_valide == true) && (duree_valide == true) && (revenue_valide == true) && (depense_valide == true)
				&& (lieu_valide == true) && (type_valide == true) && (cotisation_valide == true))
			return true;

		return false;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Image image_error = new Image("/img/error.png");
		Image image_valide = new Image("/img/valide.png");

		txt_nom.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String nom = txt_nom.getText();

				if (arg1) {
					try {

						if (nom.matches("[A-Za-z]*") == false) {
							img_nom.setImage(image_error);
							lb_nom.setText("Essayer avec chaine de caractere et sans espace");
							nom_valide = false;
						} else if (nom.matches("") == true) {
							img_nom.setImage(image_error);
							nom_valide = false;
						} else {
							img_nom.setImage(image_valide);
							lb_nom.setText("");
							nom_valide = true;
						}

					} catch (Exception e) {
						nom_valide = false;
					}

				}
			}

		});

		txt_duree.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String txtduree = txt_duree.getText();

				if (arg1)
					try {
						Integer.parseInt(txtduree);
						if (txtduree.matches("[0-9]*") == false) {

							img_duree.setImage(image_error);
							lb_duree.setText("Duree Invalid");
							duree_valide = false;

						}

						else if (txt_duree.getText().isEmpty()) {

							img_duree.setImage(image_error);
							duree_valide = false;

						}

						else {
							img_duree.setImage(image_valide);
							lb_duree.setText("");
							duree_valide = true;
						}

					} catch (NumberFormatException e) {
						img_duree.setImage(image_error);
						duree_valide = false;
					}

			}

		});

		txt_revenue.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String revenue = txt_revenue.getText();
				if (arg1)
					try {
						float m = Integer.parseInt(revenue);
						if ((revenue.matches("[0-9 .]*") == false) || (m <= 0)) {
							img_revenue.setImage(image_error);
							lb_revenue.setText("Revenue Invalid !!");
							revenue_valide = false;
						} else if (txt_revenue.getText().isEmpty()) {
							img_revenue.setImage(image_error);
							revenue_valide = false;

						}

						else {
							img_revenue.setImage(image_valide);
							lb_revenue.setText("");
							revenue_valide = true;
						}

					} catch (Exception e) {
						img_revenue.setImage(image_error);
						revenue_valide = false;
					}

			}

		});

		txt_depense.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String depense = txt_depense.getText();
				if (arg1)
					try {
						float m = Integer.parseInt(depense);
						if ((depense.matches("[0-9 .]*") == false) || (m <= 0)) {
							img_depense.setImage(image_error);
							lb_depense.setText("Depense  Invalid !!");
							depense_valide = false;
						} else if (txt_depense.getText().isEmpty()) {
							img_depense.setImage(image_error);
							depense_valide = false;

						}

						else {
							img_depense.setImage(image_valide);
							lb_depense.setText("");
							depense_valide = true;
						}

					} catch (Exception e) {
						img_depense.setImage(image_error);
						depense_valide = false;
					}

			}

		});

		txt_lieu.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String lieu = txt_lieu.getText();
				if (arg1)
					try {

						if (lieu.matches("[A-Za-z]*") == false) {
							img_lieu.setImage(image_error);
							lb_lieu.setText("Essayer avec chaine de caractere");
							lieu_valide = false;
						} else if (lieu.matches("") == true) {
							img_lieu.setImage(image_error);
							lieu_valide = false;
						} else {
							img_lieu.setImage(image_valide);
							lb_lieu.setText("");
							lieu_valide = true;
						}

					} catch (Exception e) {
						lieu_valide = false;
					}

			}

		});

		txt_type.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String type = txt_type.getText();
				if (arg1)
					try {

						if (type.matches("[A-Za-z]*") == false) {
							img_type.setImage(image_error);
							lb_type.setText("Essayer avec chaine de caractere");
							type_valide = false;
						} else if (type.matches("") == true) {
							img_type.setImage(image_error);
							type_valide = false;
						} else {
							img_type.setImage(image_valide);
							lb_type.setText("");
							type_valide = true;
						}

					} catch (Exception e) {
						lieu_valide = false;
					}

			}

		});
		txt_cotisation.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String cotisation = txt_cotisation.getText();
				if (arg1)
					try {
						float m = Integer.parseInt(cotisation);
						if ((cotisation.matches("[0-9.]*") == false) || (m <= 0)) {
							img_cotisation.setImage(image_error);
							lb_cotisation.setText("cotisation Invalid !!");
							cotisation_valide = false;
						} else if (txt_cotisation.getText().isEmpty()) {
							img_cotisation.setImage(image_error);
							cotisation_valide = false;

						}

						else {
							img_cotisation.setImage(image_valide);
							lb_cotisation.setText("");
							cotisation_valide = true;
						}

					} catch (Exception e) {
						img_cotisation.setImage(image_error);
						cotisation_valide = false;
					}

			}

		});

	}

}
