
package Project;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import DAO.Connexion;
import DAO.DAO_Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class Login_Controller implements Initializable {
    
   
    @FXML 
    private TextField  txt_LOGIN;
    
     @FXML 
    private TextField txt_PASS;
     @FXML
     Label db_lbl;
     @FXML
     Label Error_lbl;
     
     
     public void StatusDB (ActionEvent e)throws IOException,SQLException{
     
         
         if(!Connexion.getConnexion().isClosed()){
             db_lbl.setText("Connecter");
             
         } else {
             db_lbl.setText("Pas de Connection ");
        }
         
     } 

    
    
    @FXML
    private void Seconnecter(ActionEvent event) throws IOException{
        
        String Login =txt_LOGIN.getText();
        String Pass = txt_PASS.getText();
        
      DAO_Login DAO =new DAO_Login();
      
      
      boolean test =DAO.seconnecter(Login,Pass);
      
      if(test==true){
    	  Stage stage = new Stage();
    	  Parent root = FXMLLoader.load(getClass().getResource("Principale.fxml"));
          
          Scene scene = new Scene(root);
          stage.setScene(scene);
          stage.show();
    	 
      }
      
          
      
      else
          Error_lbl.setText("Identifiant ou Mot de pass Incorrect");
     
     
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
