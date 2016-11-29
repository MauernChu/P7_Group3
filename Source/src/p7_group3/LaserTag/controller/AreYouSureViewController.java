/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p7_group3.LaserTag.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mette
 */
public class AreYouSureViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    //when pushing yes button, the pop up window closes
    @FXML
    private javafx.scene.control.Button yesButton;

    @FXML
    private void yesButtonAction() {
        Stage stage = (Stage) yesButton.getScene().getWindow();
        stage.close();
    }
    
    //when pushing no button, the pop up window closes
    @FXML
    private javafx.scene.control.Button noButton;

    @FXML
    private void noButtonAction() {
        Stage stage = (Stage) noButton.getScene().getWindow();
        stage.close();
    }


}
