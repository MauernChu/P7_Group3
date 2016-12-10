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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import p7_group3.LaserTag.database.MaintenanceDAO;
import p7_group3.LaserTag.database.SqliteConnectionImpl;

/**
 * FXML Controller class
 *
 * @author karatinka
 */
public class MaintenancePopUpViewController implements Initializable {

    public ChargingViewController chargingViewController;
    public MaintenanceDAO maintenanceDAO;
    public String damageDescription;
    public String nameDiscoveredDamage;
    public String problem;
    
    @FXML
    public TextField DamageDescription;
    
    @FXML
    public TextField name;
    
    @FXML
    public MenuItem problemText;
    
    @FXML
    public MenuItem problemText1;
    
    @FXML
    public MenuItem problemText2;
    
    @FXML
    public MenuItem problemText3;

   
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        maintenanceDAO = new MaintenanceDAO(new SqliteConnectionImpl());
        // TODO
    }
    //when pushing Submit button, the pop up window closes
    @FXML
    private javafx.scene.control.Button submitButton;

    @FXML
    public void submitButtonAction() {
        Stage stage = (Stage) submitButton.getScene().getWindow();
        damageDescription = DamageDescription.getText();
        nameDiscoveredDamage = name.getText();
        maintenanceDAO.GetProblemDescription(damageDescription, nameDiscoveredDamage, problem);
        chargingViewController.loadDatabaseCharging();
        stage.close();
    }
    @FXML
    public MenuButton defineProblem;

    //when pushing Cancle button, the pop up window closes
    @FXML
    private javafx.scene.control.Button cancleButton;

    @FXML
    public void cancleButtonAction() {
        Stage stage = (Stage) cancleButton.getScene().getWindow();
        stage.close();
    }

    //Method for drop down menu in "Send to maintenance" pop up
    public void dropDownMenuForMaintenancePopUp(ActionEvent event) throws IOException {
        MenuButton m = new MenuButton("defineProblem");
        m.getItems().addAll(new MenuItem("trigger"), new MenuItem("sensor"), new MenuItem("can't turn on"), new MenuItem("other"));
    }
    
    public void Trigger (ActionEvent event){
        DamageDescription.setText("Trigger cannot be pulled");
        defineProblem.setText("Trigger");
        problemText.setText("Trigger");
        problem = problemText.getText();

    };
    
    public void Sensor (ActionEvent event){
        DamageDescription.setText("Sensor cannot track the laser beams");
        defineProblem.setText("Sensor");
        problemText1.setText("Sensor");
        problem = problemText1.getText();
    };
    
    public void CantTurnOn (ActionEvent event){
        DamageDescription.setText("Equipment cannot turn on");
        defineProblem.setText("Cannot Turn On");
        problemText2.setText("Cannot turn on");
        problem = problemText2.getText();
    };
    
    public void Other (ActionEvent event){
        DamageDescription.setText("");
        defineProblem.setText("Other");
        problemText3.setText("Other");
        problem = problemText3.getText();
    };
    
    //Method for showing the chosen equipment name in the drop down menu
    public void showSelectedProblemWhenChoosing(ActionEvent event) throws IOException {
        MenuItem menu = (MenuItem) event.getSource();
        defineProblem.setText(menu.getText());

    }
    public void setChargingViewController(ChargingViewController tableViewController) {
        this.chargingViewController = tableViewController;
    }
    
    
}