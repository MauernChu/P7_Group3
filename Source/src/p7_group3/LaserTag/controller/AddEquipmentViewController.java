package p7_group3.LaserTag.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class AddEquipmentViewController implements Initializable {
    
     public ChargingViewController chargingViewController;
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
     //when pushing Cancle button, the pop up window closes
    @FXML
    private javafx.scene.control.Button cancleAddEquipmentButton;
    
    @FXML
    public void cancleAddEquipmentButtonAction() {
        Stage stage = (Stage) cancleAddEquipmentButton.getScene().getWindow();
        stage.close();
    }
    
     //when pushing Submit button new equipment will be added to database
    @FXML
    private javafx.scene.control.Button submitAddEquipmentButton;
    
    @FXML
    public void submitAddEquipmentButtonAction() {
        Stage stage = (Stage) submitAddEquipmentButton.getScene().getWindow();
        stage.close();
    }
    
    public void setChargingViewController(ChargingViewController tableViewController) {
        this.chargingViewController = tableViewController;
    }
    
}
