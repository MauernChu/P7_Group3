package p7_group3.LaserTag.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import p7_group3.LaserTag.model.Table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Jannik Boldsen
 */

public class FXMLController implements Initializable {
    
    
    // DEFINE TABLE
    @FXML
    TableView<Table> tableID;
    @FXML
    TableColumn<Table, Integer> eID;
    @FXML
    TableColumn<Table, String> dateCharged;
    
    //Variables for Drop Down Menu
    @FXML
    private MenuButton menuButtonTest; 
    @FXML
    private MenuItem allEquipment;
    @FXML
    private MenuItem guns;
    @FXML
    private MenuItem medicalBoxes;
    @FXML
    private MenuItem gameControllers;
    @FXML
    private MenuItem dominationBoxes;
    
    // DEFINE VARIABLES
    
    // CREATE TABLE DATA
    final ObservableList<Table> data = FXCollections.observableArrayList(
            new Table(3, "21/11/2016")
    );
    
 
    //  Initializes the controller class.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        eID.setCellValueFactory(new PropertyValueFactory<>("rID"));
        dateCharged.setCellValueFactory(new PropertyValueFactory<>("rDateCharged"));

        
        tableID.setItems(data);
    
    }   
    // Method for pushing maintenance/login page
    public void openMaintenanceView(ActionEvent event) throws IOException {
        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("p7_group3/LaserTag/view/LoginView.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
    }
    
    //Method for drop down menu
    public void dropDownMenu (ActionEvent event) throws IOException {
    MenuButton m = new MenuButton("menuButtonTest");
    m.getItems().addAll(new MenuItem ("allEquipment"), new MenuItem ("guns"), new MenuItem ("medicalBoxes"), new MenuItem ("gameControllers"), new MenuItem ("dominationBoxes"));
}
 
  }
