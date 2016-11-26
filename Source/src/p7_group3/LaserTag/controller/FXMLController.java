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
    
    public void openMaintenanceView(ActionEvent event) throws IOException {
        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("p7_group3/LaserTag/view/LoginView.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
    }
    
}
