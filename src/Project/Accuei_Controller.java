package Project;

import java.io.IOException;
import java.lang.System.Logger;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Accuei_Controller implements Initializable {
	@FXML
	private Label btn;
	@FXML
	private JFXDrawer drawer;
	@FXML
	private JFXHamburger ham;
	@FXML
	VBox box;
	@FXML
	Pane pane_principale;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			box = FXMLLoader.load(getClass().getResource("Drawer.fxml"));
			drawer.setSidePane(box);
			

			for (Node node : box.getChildren()) {
				node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
					if (node.getAccessibleText() != null) {
						switch (node.getAccessibleText()) {
						case "Accuiel":
							Pane fenetre_Accueil;
							try {
								fenetre_Accueil = FXMLLoader
										.load(getClass().getResource("Accueil.fxml"));
								pane_principale.getChildren().add(fenetre_Accueil);
							} catch (IOException e1) {

								e1.printStackTrace();
							}
							

							break;
						case "Membre":
							Pane fenetre_gererMembre;
							try {
								fenetre_gererMembre = FXMLLoader
										.load(getClass().getResource("Fenetre_gererMembre.fxml"));
								pane_principale.getChildren().add(fenetre_gererMembre);
							} catch (IOException e1) {

								e1.printStackTrace();
							}

							break;
						case "Revenue":
							Pane fenetre_Revenue;
							try {
								fenetre_Revenue = FXMLLoader
										.load(getClass().getResource("Fenetre_gererRevenue.fxml"));
								pane_principale.getChildren().add(fenetre_Revenue);
							} catch (IOException e1) {

								e1.printStackTrace();
							}

							break;
						case "Depense":
							Pane fenetre_Depense;
							try {
								fenetre_Depense = FXMLLoader
										.load(getClass().getResource("Fenetre_gererDepense.fxml"));
								pane_principale.getChildren().add(fenetre_Depense);
							} catch (IOException e1) {

								e1.printStackTrace();
							}

							break;
						case "Evenement":
							Pane fenetre_Evenement;
							try {
								fenetre_Evenement = FXMLLoader
										.load(getClass().getResource("Fenetre_gererEvennement.fxml"));
								pane_principale.getChildren().add(fenetre_Evenement);
							} catch (IOException e1) {

								e1.printStackTrace();
							}

							break;
						case "Necessiteuse":
							Pane fenetre_Necessiteuse;
							try {
								fenetre_Necessiteuse = FXMLLoader
										.load(getClass().getResource("Fenetre_gerePersonneNecessiteuse.fxml"));
								pane_principale.getChildren().add(fenetre_Necessiteuse);
							} catch (IOException e1) {

								e1.printStackTrace();
							}

							break;
							case "parametrage":
								Pane fenetre_parametrage;
								try {
									fenetre_parametrage = FXMLLoader
											.load(getClass().getResource("Parametrage.fxml"));
									pane_principale.getChildren().add(fenetre_parametrage);
								} catch (IOException e1) {

									e1.printStackTrace();
								}

								break;
							

						}
					}
				});
			}
		} catch (IOException ex) {
		}

		HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(ham);
		transition.setRate(-1);
		ham.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
			transition.setRate(transition.getRate() * -1);
			transition.play();

			if (drawer.isShown()) {
				drawer.close();
			} else
				drawer.open();
		});

	}

}
