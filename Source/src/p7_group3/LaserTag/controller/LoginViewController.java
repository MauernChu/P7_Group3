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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import p7_group3.LaserTag.database.AdminAccessDAO;
import p7_group3.LaserTag.database.SqliteConnectionImpl;

/**
 * FXML Controller class
 *
 * @author Mette
 */
public class LoginViewController implements Initializable {

    AdminAccessDAO adminAccessDao = new AdminAccessDAO(new SqliteConnectionImpl());
            
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
        
        boolean canAccess = adminAccessDao.CanAccess(txtUserName.getText(), txtPassword.getText());
        
        if (canAccess == true) { //checks if the password and username matches UserNameDb and PasswordDb
            lblStatus.setText(""); //Connects to lblStatus button, and states the given text
            Parent maintenancePage = FXMLLoader.load(getClass().getClassLoader().getResource("p7_group3/LaserTag/view/MaintenanceView.fxml"));
            Scene maintenanceScene = new Scene(maintenancePage);
            Stage login_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            login_stage.hide();
            login_stage.setScene(maintenanceScene);
            login_stage.show();
        } else {
            lblStatus.setText("Login failed");
        }
    }
}
