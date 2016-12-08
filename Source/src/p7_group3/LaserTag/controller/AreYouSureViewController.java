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
import javafx.stage.Stage;
import p7_group3.LaserTag.database.EquipmentDAO;
import p7_group3.LaserTag.database.SqliteConnectionImpl;


/**
 * FXML Controller class
 *
 * @author Mette
 */
public class AreYouSureViewController implements Initializable {

    private ChargingViewController chargingViewController;
        
    public EquipmentDAO equipmentDAO;
   
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        equipmentDAO = new EquipmentDAO(new SqliteConnectionImpl());
        // TODO
    }
    //when pushing yes button, the pop up window closes
    @FXML
    private javafx.scene.control.Button yesButton;

    @FXML
    public void yesButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) yesButton.getScene().getWindow();
        
        equipmentDAO.GetBySqlSearchRead();
        chargingViewController.loadDatabaseCharging();
        
        stage.close();
    }
    
    //when pushing no button, the pop up window closes
    @FXML
    private javafx.scene.control.Button noButton;

    @FXML
    public void noButtonAction() {
        Stage stage = (Stage) noButton.getScene().getWindow();
        stage.close();
    }
    
    public void setChargingViewController(ChargingViewController tableViewController) {
        this.chargingViewController = tableViewController;
    }

}
