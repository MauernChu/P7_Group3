package p7_group3;

import java.net.URL;
import java.util.ResourceBundle;

import TableModel.Table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;


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
   
    
}
