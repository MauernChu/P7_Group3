package p7_group3.LaserTag.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;
import p7_group3.LaserTag.MainApplication;
import p7_group3.LaserTag.database.EquipmentDAO;
import p7_group3.LaserTag.database.SqliteConnectionImpl;
import p7_group3.LaserTag.model.Equipment;
import p7_group3.LaserTag.model.MaintenanceTable;
import p7_group3.LaserTag.model.UsingTable;

/**
 * FXML Controller class
 *
 * @author Jannik Boldsen
 */
public class TableViewControllerWithModels implements Initializable {

    //Main Application reference
    MainApplication mainApplication;

    // Front page private variables
    private ObservableList<Equipment> equipmentList;

    //Database references
    private EquipmentDAO equipmentDAO;

    // DEFINE TABLEVIEW AND COLUMNS FOR FRONT PAGE
    @FXML
    TableView<Equipment> tableID;
    @FXML
    TableColumn<Equipment, String> eqID;
    @FXML
    TableColumn<Equipment, String> chargeDateID;

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
        equipmentDAO = new EquipmentDAO(new SqliteConnectionImpl());
        loadDatabaseChargingAtStart();
    }

    //Method for loading database for CHARGING
    @FXML
    public void loadDatabaseCharging(ActionEvent event) throws IOException {
        ChangeSceneToTableView();
        loadDatabaseChargingAtStart();
    }

    private void loadDatabaseChargingAtStart() {
        equipmentList = FXCollections.observableArrayList();
        equipmentList.addAll(equipmentDAO.GetAllEquipments());

        eqID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String)cellData.getValue().name)));
        chargeDateID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        
        tableID.setItems(null);
        tableID.setItems(equipmentList);
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
}
