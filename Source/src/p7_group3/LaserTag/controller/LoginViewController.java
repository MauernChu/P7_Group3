/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p7_group3.LaserTag.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mette
 */
public class LoginViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
	@FXML
	private Label lblStatus; //Creates variable connected to Scenebuilder through ID

	@FXML
	private Label lblTitel;
	
	@FXML
	private TextField txtUserName;
	
	@FXML
	private TextField txtPassword;
	
	public void Login(ActionEvent event) throws IOException { 
		if (txtUserName.getText().equals("user") && txtPassword.getText().equals("pass")) { //checks if the password and username matches the predifined values
			lblStatus.setText(""); //Connects to lblStatus button, and states the given text
			Stage stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("p7_group3/LaserTag/view/MaintenanceView.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
		} else{
			lblStatus.setText("Login failed");
		}
	}
}
