package Project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Principale_Controller implements Initializable {

	@FXML
	private JFXHamburger ham;
	@FXML
	private JFXDrawer drawer;
	@FXML
	private JFXButton btn_gererM;
	@FXML
	private JFXButton btn_accuie;
	@FXML
	private AnchorPane pn_gererM;
	@FXML
	private AnchorPane pn_accuei;
	@FXML
	private Pane pane_principale;

	public void GereMembreButtonAction(ActionEvent e) throws IOException {

		Pane fenetre_gererMembre = FXMLLoader.load(getClass().getResource("Fenetre_gererMembre.fxml"));
		pane_principale.getChildren().add(fenetre_gererMembre);

	}

	public void GereRevenueButtonAction(ActionEvent e) throws IOException {

		Pane fenetre_gererRevenue = FXMLLoader.load(getClass().getResource("Fenetre_gererRevenue.fxml"));
		pane_principale.getChildren().add(fenetre_gererRevenue);

	}

	public void AcceuilButtonAction(ActionEvent e) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("Prpl.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		


	}
	public void GereDepenseButtonAction(ActionEvent e) throws IOException {

		Pane fenetre_gererDepense = FXMLLoader.load(getClass().getResource("Fenetre_gererDepense.fxml"));
		pane_principale.getChildren().add(fenetre_gererDepense);

	}
	public void EvenementButtonAction(ActionEvent e) throws IOException {

		Pane fenetre_Evenement = FXMLLoader.load(getClass().getResource("Fenetre_gererEvennement.fxml"));
		pane_principale.getChildren().add(fenetre_Evenement);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
        /*try {
            VBox box = FXMLLoader.load(getClass().getResource("Drawer.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
        }
        
        
        
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(ham);
        transition.setRate(-1);
        ham.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
            transition.setRate(transition.getRate()*-1);
            transition.play();
            
            if(drawer.isShown())
            {
                drawer.close();
            }else
                drawer.open();
        });
		*/

	}

}
