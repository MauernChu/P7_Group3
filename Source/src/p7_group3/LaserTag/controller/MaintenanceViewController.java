/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p7_group3.LaserTag.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import p7_group3.LaserTag.database.EquipmentAccessDAO;
import p7_group3.LaserTag.model.MaintenanceTable;
import p7_group3.LaserTag.model.Table;

/**
 * FXML Controller class
 *
 * @author Mette
 */
public class MaintenanceViewController extends TableViewController implements Initializable {
    
    private ObservableList<MaintenanceTable> Maindata;
    private EquipmentAccessDAO dc;
    

    // DEFINE TABLE
    @FXML
    TableView<MaintenanceTable> MainTableID;
    @FXML
    TableColumn<MaintenanceTable, String> DamagedID;
    @FXML
    TableColumn<MaintenanceTable, String> DesID;
    
    
    @FXML
    private Button loadMaintenance;

    
    //  Initializes the controller class.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new EquipmentAccessDAO();
        
    }
    
      //Method for loading database button
    @FXML
    public void loadMaintenanceDatabase(ActionEvent event){
    try {
            Connection conn = dc.Connect();
            
            Maindata = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM MaintenanceTable");
            while (rs.next()) {
                //get string from db,whichever way 
                //data.add(new Table(rs.getString(1), rs.getDouble(2)));
                Maindata.add(new MaintenanceTable(rs.getString(1), rs.getString(2)));
                //rs.updateTime(, time);
                //data.add(new Table(rs.updateTime(2, time)));
                //data.add(new Table(rs.getString(1), rs.getDouble("INSERT INTO Test (Date of charge) VALUES('" + time + "')")));
               
            }

        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        DamagedID.setCellValueFactory(new PropertyValueFactory<>("damID"));
        DesID.setCellValueFactory(new PropertyValueFactory<>("desID"));
        
        MainTableID.setItems(null);
        MainTableID.setItems(Maindata);
    }
    
}
