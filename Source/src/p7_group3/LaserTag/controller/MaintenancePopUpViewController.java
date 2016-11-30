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
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author karatinka
 */
public class MaintenancePopUpViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    //when pushing Submit button, the pop up window closes
    @FXML
    private javafx.scene.control.Button submitButton;

    @FXML
    private void submitButtonAction() {
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    private MenuButton defineProblem;

    //when pushing Cancle button, the pop up window closes
    @FXML
    private javafx.scene.control.Button cancleButton;

    @FXML
    private void cancleButtonAction() {
        Stage stage = (Stage) cancleButton.getScene().getWindow();
        stage.close();
    }

    //Method for drop down menu in "Send to maintenance" pop up
    public void dropDownMenuForMaintenancePopUp(ActionEvent event) throws IOException {
        MenuButton m = new MenuButton("defineProblem");
        m.getItems().addAll(new MenuItem("trigger"), new MenuItem("sensor"), new MenuItem("can't turn on"), new MenuItem("other"));
    }

    //Method for showing the chosen equipment name in the drop down menu
    public void showSelectedProblemWhenChoosing(ActionEvent event) throws IOException {
        MenuItem menu = (MenuItem) event.getSource();
        defineProblem.setText(menu.getText());

    }
}
