package p7_group3.LaserTag.controller;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;

import p7_group3.LaserTag.model.Table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import p7_group3.LaserTag.database.EquipmentAccessDAO;

/**
 * FXML Controller class
 *
 * @author Jannik Boldsen
 */
public class TableViewController implements Initializable {
    
    private ObservableList<Table> data;
    private EquipmentAccessDAO dc;
    
    private Date date;
    Time time;
    //double time = 2.5;
    //double time1 = 3.5;
    //double time2 = 4.5;
   

    // DEFINE TABLE
    @FXML
    TableView<Table> tableID;
    @FXML
    TableColumn<Table, String> eqID;
    @FXML
    TableColumn<Table, Double> chargeDateID;

    //Variables for Drop Down Menu
    @FXML
    private MenuButton selectEquipment;
    @FXML
    private Button load;
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

    //Variables for "Are you sure?" Pop up window
    @FXML
    private Button updateButton;

    //Variables for send to maintenance pop up
    @FXML
    private Button sendToMaintenanceButton;
    
    //Variables for charging/using buttons
    @FXML
    private Button usingButton;
    
    @FXML
    private Button chargingButton;


    //  Initializes the controller class.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new EquipmentAccessDAO();
        
    }

    

    // Method for pushing maintenance/login page
    public void openLoginView(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("p7_group3/LaserTag/view/LoginView.fxml"));
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("pictures/glove.png"));
        stage.setTitle("Laser-tag application");
        //((Node)(event.getSource())).getScene().getWindow().hide();
        stage.setScene(scene);
        stage.show();
    }

    //Method for loading database button
    @FXML
    public void loadDatabase(ActionEvent event){
    try {
            Connection conn = dc.Connect();
            
            data = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Test");
            while (rs.next()) {
                //get string from db,whichever way 
                //data.add(new Table(rs.getString(1), rs.getDouble(2)));
                data.add(new Table(rs.getString(1), rs.getDouble(2)));
                //rs.updateTime(, time);
                //data.add(new Table(rs.updateTime(2, time)));
                //data.add(new Table(rs.getString(1), rs.getDouble("INSERT INTO Test (Date of charge) VALUES('" + time + "')")));
               
            }

        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        eqID.setCellValueFactory(new PropertyValueFactory<>("rID"));
        chargeDateID.setCellValueFactory(new PropertyValueFactory<>("dID"));
        
        tableID.setItems(null);
        tableID.setItems(data);
    }
    

    // Method for pushing "using" page
    public void openUsingView(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("p7_group3/LaserTag/view/UsingView.fxml"));
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("pictures/glove.png"));
        stage.setTitle("Laser-tag application");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    // Method for pushing "charging" page
    public void openChargingPage(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("p7_group3/LaserTag/view/FXML.fxml"));
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("pictures/glove.png"));
        stage.setTitle("Laser-tag application");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    
    //Method for drop down menu
    public void dropDownMenu(ActionEvent event) throws IOException {
        MenuButton m = new MenuButton("selectEquipment");
        m.getItems().addAll(new MenuItem("allEquipment"), new MenuItem("guns"), new MenuItem("medicalBoxes"), new MenuItem("gameControllers"), new MenuItem("dominationBoxes"));
    }
    //Method for showing the chosen equipment name in the drop down menu

    public void showSelectedNameOfEquipment(ActionEvent event) throws IOException {
        MenuItem menu = (MenuItem) event.getSource();
        selectEquipment.setText(menu.getText());
    }
    //Method for "Are you sure?" pop up window

    public void areYouSurePopUp(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("p7_group3/LaserTag/view/AreYouSureView.fxml"));
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("pictures/glove.png"));
        stage.setTitle("Laser-tag application");
        stage.setScene(scene);
        stage.show();

    }

    //Method for sendToMaintenance pop up window
    public void sendToMaintenancePopUp(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("p7_group3/LaserTag/view/MaintenancePopUpView.fxml"));
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("pictures/glove.png"));
        stage.setTitle("Laser-tag application");
        stage.setScene(scene);
        stage.show();
    }

}
