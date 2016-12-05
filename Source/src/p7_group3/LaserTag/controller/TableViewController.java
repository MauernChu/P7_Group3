package p7_group3.LaserTag.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.ResourceBundle;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import p7_group3.LaserTag.MainApplication;
import p7_group3.LaserTag.database.EquipmentAccessDAO;
import p7_group3.LaserTag.model.MaintenanceTable;
import p7_group3.LaserTag.model.UsingTable;

/**
 * FXML Controller class
 *
 * @author Jannik Boldsen
 */
public class TableViewController implements Initializable {

    //Main Application reference
    MainApplication mainApplication;

    // Front page private variables
    private ObservableList<Table> data;
    private EquipmentAccessDAO dc; // Used by both front page and maintenance page

    // Maintenance page private variables 
    private ObservableList<MaintenanceTable> Maindata;
    
    // Using page  page private variables 
    private ObservableList<UsingTable> Usingdata;

    // Variables for storing the time
    private Date date;
    Time time;

    // DEFINE TABLEVIEW AND COLUMNS FOR FRONT PAGE
    @FXML
    TableView<Table> tableID;
    @FXML
    TableColumn<Table, String> eqID;
    @FXML
    TableColumn<Table, Double> chargeDateID;
    
    // DEFINE TABLEVIEW AND COLUMNS FOR USING PAGE
    @FXML
    TableView<UsingTable> usingTableID;
    @FXML
    TableColumn<UsingTable, String> usingEqID;
    @FXML
    TableColumn<UsingTable, Double> chargeUsingDateID;

    // DEFINE TABLEVIEW AND COLUMNS FOR MAINTENANCE PAGE
    @FXML
    TableView<MaintenanceTable> MainTableID;
    @FXML
    TableColumn<MaintenanceTable, String> DamagedID;
    @FXML
    TableColumn<MaintenanceTable, String> DesID;

    // Load database button
    @FXML
    private Button load;

    // Load database button for Maintenance
    @FXML
    private Button loadMaintenance;

    //Variables for Drop Down Menu SHARED BETWEEN ALL PAGES
    @FXML
    private MenuButton selectEquipment;
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

    @FXML
    private Button chargingOpeningPopUpButton;

    //  Initializes the controller class.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new EquipmentAccessDAO();
        loadDatabaseChargingAtStart();
        

    }
    

    //Method for loading database for CHARGING
    @FXML
    public void loadDatabaseCharging(ActionEvent event) throws IOException {
        ChangeSceneToTableView();
        loadDatabaseChargingAtStart();
    }

    private void loadDatabaseChargingAtStart() {
        try {
            Connection conn = dc.Connect();

            data = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM MainDatabase order by TimePutToChargeNumeric asc");
            while (rs.next()) {
                //get string from db,whichever way 
                //data.add(new Table(rs.getString(1), rs.getDouble(2)));
                data.add(new Table(rs.getString(2), rs.getDouble(4)));
                //rs.updateTime(, time);
                //data.add(new Table(rs.updateTime(2, time)));
                //data.add(new Table(rs.getString(1), rs.getDouble("INSERT INTO Test (Date of charge) VALUES('" + time + "')")));

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        eqID.setCellValueFactory(new PropertyValueFactory<>("rID"));
        chargeDateID.setCellValueFactory(new PropertyValueFactory<>("dID"));

        tableID.setItems(null);
        tableID.setItems(data);
    }

    //Method for loading database on the front page
    @FXML
    public void loadDatabaseUsing(ActionEvent event) throws IOException {
        ChangeSceneToTableView();
        
        try {
            Connection conn = dc.Connect();

            data = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM MainDatabase order by TimePutToChargeNumeric desc");
            while (rs.next()) {
                //get string from db,whichever way 
                data.add(new Table(rs.getString(2), rs.getDouble(4)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        eqID.setCellValueFactory(new PropertyValueFactory<>("rID"));
        chargeDateID.setCellValueFactory(new PropertyValueFactory<>("dID"));

        tableID.setItems(null);
        tableID.setItems(data);
    }

    //Method for loading database for MAINTENANCE
    @FXML
    public void loadDatabaseMaintenance(ActionEvent event) throws IOException {
        
        try {
            Connection conn = dc.Connect();

            Maindata = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM MainDatabase");
            //Statement st = conn.createStatement();
            while (rs.next()) {
                //get string from db,whichever way 
                Maindata.add(new MaintenanceTable(rs.getString(2), rs.getString(4)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        DamagedID.setCellValueFactory(new PropertyValueFactory<>("damID"));
        DesID.setCellValueFactory(new PropertyValueFactory<>("desID"));

        MainTableID.setItems(null);
        MainTableID.setItems(Maindata);
    }
    
    // Method for pushing maintenance/login page
    public void openLoginView(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("p7_group3/LaserTag/view/LoginView.fxml"));
        Parent root = (Parent) loader.load();
        Scene loginScene = new Scene(root);
        Stage loginStage = new Stage();
        loginStage.getIcons().add(new Image("pictures/glove.png"));
        loginStage.setTitle("Laser-tag application");
        loginStage.setScene(loginScene);
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.show();
        
        LoginViewController loginViewController = loader.getController();
        loginViewController.setTableViewController(this);
    }

    // Method for loging in to "maintenance" scene
    public void openMaintenanceView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("p7_group3/LaserTag/view/MaintenanceView.fxml"));
        loader.setController(this);
        Parent maintenanceParent = (Parent) loader.load();
        Scene maintenanceScene = new Scene(maintenanceParent);       
        mainApplication.primaryStage.setScene(maintenanceScene);
    try {
            Connection conn = dc.Connect();

            Maindata = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM MainDatabase");
            //Statement st = conn.createStatement();
            while (rs.next()) {
                //get string from db,whichever way 
                Maindata.add(new MaintenanceTable(rs.getString(2), rs.getString(4)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        DamagedID.setCellValueFactory(new PropertyValueFactory<>("damID"));
        DesID.setCellValueFactory(new PropertyValueFactory<>("desID"));

        MainTableID.setItems(null);
        MainTableID.setItems(Maindata);
    }

    //Method for drop down menu
    public void dropDownMenu(ActionEvent event) throws IOException {
        MenuButton m = new MenuButton("selectEquipment");
        m.getItems().addAll(new MenuItem("allEquipment"), new MenuItem("guns"), new MenuItem("medicalBoxes"), new MenuItem("gameControllers"), new MenuItem("dominationBoxes"));
    }

    //Method for showing the chosen equipment name in the drop down menu
   /* public void showSelectedNameOfEquipment(ActionEvent event) throws IOException {
        MenuItem menu = (MenuItem) event.getSource();
        selectEquipment.setText(menu.getText());
    }*/
    
    //Method for loading ALL EQUIPMENT NOT SORTED IN MAIN SCREEN
    @FXML
    public void showSelectedNameOfEquipment (ActionEvent event) throws IOException {
        try {
            Connection conn = dc.Connect();

            data = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM MainDatabase");
            while (rs.next()) {
                //get string from db,whichever way 
                //data.add(new Table(rs.getString(1), rs.getDouble(2)));
                data.add(new Table(rs.getString(2), rs.getDouble(4)));
                //rs.updateTime(, time);
                //data.add(new Table(rs.updateTime(2, time)));
                //data.add(new Table(rs.getString(1), rs.getDouble("INSERT INTO Test (Date of charge) VALUES('" + time + "')")));

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        eqID.setCellValueFactory(new PropertyValueFactory<>("rID"));
        chargeDateID.setCellValueFactory(new PropertyValueFactory<>("dID"));

        tableID.setItems(null);
        tableID.setItems(data);
    }
    
    
    //Method for loading ALL EQUIPMENT NOT SORTED IN MAINTENANCE
    @FXML
    public void showSelectedNameOfEquipmentMaintenance (ActionEvent event) throws IOException {
        try {
            Connection conn = dc.Connect();

            Maindata = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM MainDatabase");
            while (rs.next()) {
                //get string from db,whichever way 
                //data.add(new Table(rs.getString(1), rs.getDouble(2)));
                Maindata.add(new MaintenanceTable(rs.getString(2), rs.getString(4)));
                //rs.updateTime(, time);
                //data.add(new Table(rs.updateTime(2, time)));
                //data.add(new Table(rs.getString(1), rs.getDouble("INSERT INTO Test (Date of charge) VALUES('" + time + "')")));

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        DamagedID.setCellValueFactory(new PropertyValueFactory<>("damID"));
        DesID.setCellValueFactory(new PropertyValueFactory<>("desID"));

        MainTableID.setItems(null);
        MainTableID.setItems(Maindata);
    }
    
    //Method for loading GUNS ONLY IN MAIN SCREEN
    @FXML
    public void showGuns (ActionEvent event) throws IOException {
        try {
            Connection conn = dc.Connect();

            data = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%GU%'");
            while (rs.next()) {
                //get string from db,whichever way 
                //data.add(new Table(rs.getString(1), rs.getDouble(2)));
                data.add(new Table(rs.getString(2), rs.getDouble(4)));
                //rs.updateTime(, time);
                //data.add(new Table(rs.updateTime(2, time)));
                //data.add(new Table(rs.getString(1), rs.getDouble("INSERT INTO Test (Date of charge) VALUES('" + time + "')")));

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        eqID.setCellValueFactory(new PropertyValueFactory<>("rID"));
        chargeDateID.setCellValueFactory(new PropertyValueFactory<>("dID"));

        tableID.setItems(null);
        tableID.setItems(data);
    }
    
     //Method for loading GUNS ONLY IN MAINTENANCE
    @FXML
    public void showGunsMaintenance(ActionEvent event) throws IOException {
        //ChangeSceneToTableView();
        
        try {
            Connection conn = dc.Connect();

            Maindata = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%GU%'");
            //Statement st = conn.createStatement();
            while (rs.next()) {
                //get string from db,whichever way 
                Maindata.add(new MaintenanceTable(rs.getString(2), rs.getString(4)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        DamagedID.setCellValueFactory(new PropertyValueFactory<>("damID"));
        DesID.setCellValueFactory(new PropertyValueFactory<>("desID"));

        MainTableID.setItems(null);
        MainTableID.setItems(Maindata);
    }
    
    
    //Method for loading MEDIX BOXES ONLY IN MAIN SCREEN
    @FXML
    public void showMedic (ActionEvent event) throws IOException {
        try {
            Connection conn = dc.Connect();

            data = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%MED%'");
            while (rs.next()) {
                //get string from db,whichever way 
                //data.add(new Table(rs.getString(1), rs.getDouble(2)));
                data.add(new Table(rs.getString(2), rs.getDouble(4)));
                //rs.updateTime(, time);
                //data.add(new Table(rs.updateTime(2, time)));
                //data.add(new Table(rs.getString(1), rs.getDouble("INSERT INTO Test (Date of charge) VALUES('" + time + "')")));

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        eqID.setCellValueFactory(new PropertyValueFactory<>("rID"));
        chargeDateID.setCellValueFactory(new PropertyValueFactory<>("dID"));

        tableID.setItems(null);
        tableID.setItems(data);
    }
    
    //Method for loading MEDIC BOXES ONLY IN MAINTENANCE
    @FXML
    public void showMedicMaintenance(ActionEvent event) throws IOException {
        //ChangeSceneToTableView();
        
        try {
            Connection conn = dc.Connect();

            Maindata = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%MED%'");
            //Statement st = conn.createStatement();
            while (rs.next()) {
                //get string from db,whichever way 
                Maindata.add(new MaintenanceTable(rs.getString(2), rs.getString(4)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        DamagedID.setCellValueFactory(new PropertyValueFactory<>("damID"));
        DesID.setCellValueFactory(new PropertyValueFactory<>("desID"));

        MainTableID.setItems(null);
        MainTableID.setItems(Maindata);
    }
    
    
    //Method for loading GAME CONTROLLER ONLY IN MAIN SCREEN
    @FXML
    public void showController (ActionEvent event) throws IOException {
        try {
            Connection conn = dc.Connect();

            data = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%CO%'");
            while (rs.next()) {
                //get string from db,whichever way 
                //data.add(new Table(rs.getString(1), rs.getDouble(2)));
                data.add(new Table(rs.getString(2), rs.getDouble(4)));
                //rs.updateTime(, time);
                //data.add(new Table(rs.updateTime(2, time)));
                //data.add(new Table(rs.getString(1), rs.getDouble("INSERT INTO Test (Date of charge) VALUES('" + time + "')")));

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        eqID.setCellValueFactory(new PropertyValueFactory<>("rID"));
        chargeDateID.setCellValueFactory(new PropertyValueFactory<>("dID"));

        tableID.setItems(null);
        tableID.setItems(data);
    }
    
    //Method for loading GAME CONTROLLER ONLY IN MAINTENANCE
    @FXML
    public void showControllerMaintenance(ActionEvent event) throws IOException {
        //ChangeSceneToTableView();
        
        try {
            Connection conn = dc.Connect();

            Maindata = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%CO%'");
            //Statement st = conn.createStatement();
            while (rs.next()) {
                //get string from db,whichever way 
                Maindata.add(new MaintenanceTable(rs.getString(2), rs.getString(4)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        DamagedID.setCellValueFactory(new PropertyValueFactory<>("damID"));
        DesID.setCellValueFactory(new PropertyValueFactory<>("desID"));

        MainTableID.setItems(null);
        MainTableID.setItems(Maindata);
    }
    
    
    //Method for loading DOMINATION BOXES ONLY IN MAIN SCREEN
    @FXML
    public void showDomination (ActionEvent event) throws IOException {
        try {
            Connection conn = dc.Connect();

            data = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%DO%'");
            while (rs.next()) {
                //get string from db,whichever way 
                //data.add(new Table(rs.getString(1), rs.getDouble(2)));
                data.add(new Table(rs.getString(2), rs.getDouble(4)));
                //rs.updateTime(, time);
                //data.add(new Table(rs.updateTime(2, time)));
                //data.add(new Table(rs.getString(1), rs.getDouble("INSERT INTO Test (Date of charge) VALUES('" + time + "')")));

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        eqID.setCellValueFactory(new PropertyValueFactory<>("rID"));
        chargeDateID.setCellValueFactory(new PropertyValueFactory<>("dID"));

        tableID.setItems(null);
        tableID.setItems(data);
    }
    
    //Method for loading DOMINATION BOXES ONLY IN MAINTENANCE
    @FXML
    public void showDominationMaintenance(ActionEvent event) throws IOException {
        //ChangeSceneToTableView();
        
        try {
            Connection conn = dc.Connect();

            Maindata = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%DO%'");
            //Statement st = conn.createStatement();
            while (rs.next()) {
                //get string from db,whichever way 
                Maindata.add(new MaintenanceTable(rs.getString(2), rs.getString(4)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        DamagedID.setCellValueFactory(new PropertyValueFactory<>("damID"));
        DesID.setCellValueFactory(new PropertyValueFactory<>("desID"));

        MainTableID.setItems(null);
        MainTableID.setItems(Maindata);
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

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApplication
     */
    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
    
    private void ChangeSceneToTableView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("p7_group3/LaserTag/view/TableView.fxml"));
        loader.setController(this);
        Parent tableParent = (Parent) loader.load();
        Scene tableScene = new Scene(tableParent);
        mainApplication.primaryStage.setScene(tableScene);
    }
    
    private void ChangeSceneToUsingView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("p7_group3/LaserTag/view/UsingView.fxml"));
        loader.setController(this);
        Parent tableParent = (Parent) loader.load();
        Scene tableScene = new Scene(tableParent);
        mainApplication.primaryStage.setScene(tableScene);
    }
    
    // Method for pushing "using" scene
    public void openUsingView(ActionEvent event) throws IOException {
        ChangeSceneToUsingView();
        try {
            Connection conn = dc.Connect();

            Usingdata = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM MainDatabase order by TimePutToChargeNumeric desc");
            while (rs.next()) {
                //get string from db,whichever way 
                Usingdata.add(new UsingTable(rs.getString(2), rs.getDouble(4)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        usingEqID.setCellValueFactory(new PropertyValueFactory<>("urID"));
        chargeUsingDateID.setCellValueFactory(new PropertyValueFactory<>("udID"));

        usingTableID.setItems(null);
        usingTableID.setItems(Usingdata);
    }
}


