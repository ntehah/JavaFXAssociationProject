	package Project;

	import java.io.IOException;
	import java.net.URL;
	import java.time.LocalDate;
	import java.time.Period;
	import java.util.ResourceBundle;


	import DAO.DAO_PersonneNec;
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

	public class Ajout_P_Nec_Controller implements Initializable {

		@FXML
		private Label nom;
		@FXML
		private Label prenom;
		@FXML
		private Label cin;
		@FXML
		private Label date;
		@FXML
		private Label adress;
		@FXML
		private Label codepostale;
		@FXML
		private Label nbrEnfants;
		@FXML
		private Label profession;
		@FXML
		private Label revenueanel;
		@FXML
		private Label etatcivil;
		@FXML
		private Label tel ;
		@FXML
		private Label genre;
	
		
		
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
		private ImageView img_nbEnf;
		@FXML
		private ImageView img_rev;
		@FXML
		private ImageView img_prof;
		@FXML
		private ImageView img_etatCivil;
		@FXML
		private ImageView img_genre;
		

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
		private TextField txt_nbEnf;
		@FXML
		private DatePicker txt_date;
		@FXML
		private TextField txt_codepostal;
		@FXML
		private RadioButton radio_M;
		@FXML
		private RadioButton radio_D;
		@FXML
		private RadioButton radio_C;
		@FXML
		private TextField txt_pro;
		@FXML
		private TextField txt_rev;
		@FXML
		private RadioButton radio_H;
		@FXML
		private RadioButton radio_F;
		
		

		public void AjoutButtonAction(ActionEvent e) throws IOException {
			
			if(Valide()) {

			LocalDate Dateps= txt_date.getValue();
			String nom = txt_nom.getText();
			String prenom = txt_prenom.getText();
			String textcin = txt_cin.getText();
			String adress = txt_adress.getText();
			String nbenfant = txt_nbEnf.getText();
			String textTel = txt_tel.getText();
			String textcodep = txt_codepostal.getText();
			String textrevenue = txt_rev.getText();
			String pro = txt_pro.getText();
			String etat;
			String genre;
			int CIN = Integer.parseInt(textcin);
			int tele = Integer.parseInt(textTel);
			int codep = Integer.parseInt(textcodep);
			int Nbenf = Integer.parseInt(nbenfant);
			float rev =Float .parseFloat(textrevenue);
			String DateB = Dateps.toString();
			
			if (radio_C.isSelected()) {
				etat = "Célibataire";
			} else if (radio_M.isSelected()) {
				etat= "Marier";
			}
		 else {
				etat = "Divorcer";
			}
			
			if (radio_H.isSelected()) {
				genre = "Homme";
			} else {
				genre = "Femme";
			}
			
				PersonneNec test = DAO_PersonneNec.getByCIN(CIN);
				if (test != null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("L'ajout D'un Personne Necessiteux");
					alert.setHeaderText("personne Exist déja ");
					alert.setContentText("Essayer avec un nouveau Personne ");
					alert.showAndWait();

				} else {

					PersonneNec per = new PersonneNec();

					per.setNom(nom);
					per.setPrenom(prenom);
					per.setCin(CIN);
					per.setAdresse(adress);
					per.setNombreEnf(Nbenf);
					per.setTel(tele);
					per.setDate(DateB);
					per.setEtatCivil(etat);
					per.setRevenuAnnuelle(rev);
					per.setCodePostale(codep);
					per.setProfession(pro);
					per.setGenre(genre);
					int status =  DAO_PersonneNec.Ajouter(per);
					if (status > 0) {

						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("L'ajout D'un personne necessiteux");
						alert.setHeaderText("Information");
						alert.setContentText("personne est ajouter");
						alert.showAndWait();

					} else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("L'ajout D'un personne necessiteux");
						alert.setHeaderText("Information");
						alert.setContentText("Failed try again");
						alert.showAndWait();

					}
				}
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("L'ajout D'un personne necessiteux");
				alert.setHeaderText("Les Champs");
				alert.setContentText("Verifier Les champs");
				alert.showAndWait();

			}
			
		}

		public static Boolean cin_valide;
		public static Boolean nom_valide;
		public static Boolean prenom_valide;
		public static Boolean adress_valide;
		public static Boolean revenue_valide;
		public static Boolean code_valide;
		public static Boolean profession_valide;
		public static Boolean nembreEnfant_valide;
		public static Boolean tel_valide;
		public static Boolean date_valide=true;

		public static boolean Valide() {
			if ((cin_valide == true) && (nom_valide == true) && (prenom_valide == true) && (adress_valide == true)	&& 
					( revenue_valide == true) && (date_valide == true) &&
					(tel_valide == true) && (nembreEnfant_valide == true) && (code_valide == true))
				return true;

			return false;
		}

		public static int calculateAge(LocalDate DateB) {
			LocalDate today = LocalDate.now();
			if ((DateB != null)) {
				return Period.between(DateB, today).getYears();
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
								cin.setText("Cin Invalid");
								cin_valide = false;

							}

							else if (txt_cin.getText().isEmpty()) {

								img_cin.setImage(image_error);
								cin_valide = false;

							}

							else {
								img_cin.setImage(image_valide);
								cin.setText("");
								cin_valide = true;
							}

						} catch (NumberFormatException e) {
							img_cin.setImage(image_error);
							cin_valide = false;
						}

				}

			});
			
			
			

			txt_rev.focusedProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
					String revn = txt_rev.getText();

					if (arg1)
						try {
							float r =Integer.parseInt(revn);
							if (r <= 0) {

								 img_rev.setImage(image_error);
								revenueanel.setText("Revenue annuelle Invalid");
								revenue_valide = false;

							}

							else if (txt_rev.getText().isEmpty()) {

								img_rev.setImage(image_error);
								revenue_valide = false;

							}

							else {
								 img_rev.setImage(image_valide);
								revenueanel.setText("");
								revenue_valide = true;
							}

						} catch (NumberFormatException e) {
							img_rev.setImage(image_error);
							revenue_valide = false;
						}

				}
			});
			

				txt_nom.focusedProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
					String nom1 = txt_nom.getText();

					if (arg1) {
						try {

							if (nom1.matches("[A-Za-z]*") == false) {
								img_nom.setImage(image_error);
								nom.setText("Essayer avec chaine de caractere et sans espace");
								nom_valide = false;
							} else if (nom1.matches("") == true) {
								img_nom.setImage(image_error);
								nom_valide = false;
							} else {
								img_nom.setImage(image_valide);
								nom.setText("");
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
					String prenom1 = txt_prenom.getText();
					if (arg1)
						try {

							if (prenom1.matches("[A-Za-z]*") == false) {
								img_prenom.setImage(image_error);
							prenom.setText("Essayer avec chaine de caractere et sans espace");
								prenom_valide = false;
							} else if (prenom1.matches("") == true) {
								img_prenom.setImage(image_error);
								prenom_valide = false;
							} else {
								img_prenom.setImage(image_valide);
								prenom.setText("");
								prenom_valide = true;
							}

						} catch (Exception e) {
							prenom_valide = false;
						}

				}

			});

			txt_pro.focusedProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
					String profess = txt_pro.getText();
					if (arg1)
						try {

							if (profess.matches("[A-Za-z]*") == false) {
								img_prof.setImage(image_error);
								profession.setText("Essayer avec chaine de caractere et sans espace");
								profession_valide = false;
							} else if (profess.matches("") == true) {
								img_prof.setImage(image_error);
								profession_valide = false;
							} else {
								img_prof.setImage(image_valide);
								profession.setText("");
								profession_valide = true;
							}

						} catch (Exception e) {
							profession_valide = false;
						}

				}

			});
			
			txt_adress.focusedProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
					String add = txt_adress.getText();
					if (arg1)
						try {

							if (add.matches("[A-Za-z0-9 ]*") == false) {
								img_add.setImage(image_error);
								adress.setText("Essayer avec chaine de caractere");
								adress_valide = false;
							} else if (add.matches("") == true) {
								img_add.setImage(image_error);
								adress_valide = false;
							} else {
								img_add.setImage(image_valide);
								adress.setText("");
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

			txt_nbEnf.focusedProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
					String nb =txt_nbEnf.getText();
					if (arg1)
						try {
							Integer.parseInt(nb);
							if (nb.matches("[0-9]") && nb.length() != 1) {
								img_nbEnf.setImage(image_error);
								nbrEnfants.setText("nombre d'enfants  Invalid !!");
							nembreEnfant_valide = false;
							} else if (txt_nbEnf.getText().isEmpty()) {
								img_nbEnf.setImage(image_error);
								nembreEnfant_valide = false;

							} else {
								img_nbEnf.setImage(image_valide);
								nbrEnfants.setText("");
								nembreEnfant_valide = true;
							}
							
						} catch (Exception e) {
							img_nbEnf.setImage(image_error);
							nembreEnfant_valide = false;
						}

				}

			});

							txt_tel.focusedProperty().addListener(new ChangeListener<Boolean>() {

								@Override
								public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
									String tel1 = txt_tel.getText();
									if (arg1)
										try {
											Integer.parseInt(tel1);
											if (tel1.matches("[0-9]*") && tel1.length() != 8) {
												img_tel.setImage(image_error);
												tel.setText("Telephone  Invalid !!");
												tel_valide = false;
											} else if (txt_tel.getText().isEmpty()) {
												img_tel.setImage(image_error);
												tel_valide = false;

											} else {
												img_tel.setImage(image_valide);
												tel.setText("");
												tel_valide = true;
											}
							

						} catch (Exception e) {
							img_tel.setImage(image_error);
							tel_valide = false;
						}

				}

			});


			txt_date.focusedProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
			
					if (arg1)
						try {

							if (txt_date.getValue()==null) {
								img_date.setImage(image_error);
								date_valide = false;
								date.setText("");
							} 
							
							else {
								img_date.setImage(image_valide);
								date.setText("");
								date_valide = true;
							}

						} catch (Exception e) {
							img_date.setImage(image_error);
							date_valide = false;
						}

				}

			});
		

		}

}
