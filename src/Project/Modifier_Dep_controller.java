package Project;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import DAO.DAO_Depense;
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

public class Modifier_Dep_controller implements Initializable {

	@FXML
	private TextField txt_id;
	@FXML
	private TextField txt_objectif;
	@FXML
	private TextField txt_montant;

	@FXML
	private ImageView img_id;
	@FXML
	private ImageView img_montant;
	@FXML
	private ImageView img_objectif;

	@FXML
	private Label err_id;
	@FXML
	private Label err_montant;
	@FXML
	private Label err_objectif;

	public void ModifierButtonAction(ActionEvent e) throws IOException {

		if (Valide()) {
			float montant = Float.parseFloat(txt_montant.getText());
			LocalDate date = LocalDate.now();
			String dateDepense = date.toString();
			String objectif = txt_objectif.getText();
			String identifant = txt_id.getText();

			int id = Integer.parseInt(identifant);
			Depense dep = DAO_Depense.getByid(Supprimer_Dep_controller.id_Depense);
			dep.setId(id);
			dep.setMontant(montant);
			dep.setDate(dateDepense);
			dep.setObjectif(objectif);

			int status = DAO_Depense.Modifier(dep);
			if (status > 0) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Modifier un depense ");
				alert.setHeaderText("Information");
				alert.setContentText("Depense est Modifier");
				alert.showAndWait();

			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Modification D'un depense");
				alert.setHeaderText("Information");
				alert.setContentText("Failed try again");
				alert.showAndWait();

			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Modification D'un depense");
			alert.setHeaderText("Les champs !!");
			alert.setContentText("Verifier Les champs");
			alert.showAndWait();

		}
	}

	public static Boolean id_valide=true;
	public static Boolean montant_valide=false;
	public static Boolean objectif_valide=false;

	public static boolean Valide() {
		if ((id_valide == true) && (montant_valide == true) && (objectif_valide == true))
			return true;
		return false;

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Depense dep = DAO_Depense.getByid(Supprimer_Dep_controller.id_Depense);
		txt_id.setText(String.valueOf(Supprimer_Dep_controller.id_Depense));
		txt_montant.setText(String.valueOf(dep.getMontant()));
		txt_objectif.setText(dep.getObjectif());

		Image image_error = new Image("/img/error.png");
		Image image_valide = new Image("/img/valide.png");

		txt_id.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String textcin = txt_id.getText();

				if (arg1)
					try {
						int i = Integer.parseInt(textcin);
						if ((textcin.matches("[0-9]*") == false) || (i <= 0)) {

							img_id.setImage(image_error);
							err_id.setText("Id Invalid");
							id_valide = false;

						}

						else if (txt_id.getText().isEmpty()) {

							img_id.setImage(image_error);
							id_valide = false;

						}

						else {
							img_id.setImage(image_valide);
							err_id.setText("");
							id_valide = true;
						}

					} catch (NumberFormatException e) {
						img_id.setImage(image_error);
						id_valide = false;
					}

			}

		});

		txt_montant.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String montant = txt_montant.getText();
				if (arg1)
					try {
						float m = Integer.parseInt(montant);
						if ((montant.matches("[0-9.]*") == false) || (m <= 0)) {
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

		txt_objectif.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String nomBanque = txt_objectif.getText();

				if (arg1) {
					try {

						if (nomBanque.matches("[A-Za-z ]*") == false) {
							img_objectif.setImage(image_error);
							err_objectif.setText("Essayer avec chaine de caractere ");
							objectif_valide = false;
						} else if (nomBanque.matches("") == true) {
							img_objectif.setImage(image_error);
							objectif_valide = false;
						} else {
							img_objectif.setImage(image_valide);
							err_objectif.setText("");
							objectif_valide = true;
						}

					} catch (Exception e) {
						objectif_valide = false;
					}

				}
			}

		});

	}
}
