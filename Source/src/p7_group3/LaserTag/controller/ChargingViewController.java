package p7_group3.LaserTag.controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import p7_group3.LaserTag.MainApplication;
import p7_group3.LaserTag.database.AdminAccessDAO;
import p7_group3.LaserTag.database.EquipmentDAO;
import p7_group3.LaserTag.database.GameDAO;
import p7_group3.LaserTag.database.MaintenanceDAO;
import p7_group3.LaserTag.database.SqliteConnectionImpl;
import p7_group3.LaserTag.model.Equipment;

public class ChargingViewController implements Initializable {

    //Main Application reference
    MainApplication mainApplication;

    // Front page private variables
    private ObservableList<Equipment> equipmentList;
    private ObservableList<Equipment> gunList;
    private ObservableList<Equipment> medicBoxList;
    private ObservableList<Equipment> gameControllerList;
    private ObservableList<Equipment> dominationBoxList;

    // Maintenance page private variables 
    private ObservableList<Equipment> maintenanceEquipmentList;
    private ObservableList<Equipment> maintenanceGunList;
    private ObservableList<Equipment> maintenanceMedicBoxList;
    private ObservableList<Equipment> maintenanceGameControllerList;
    private ObservableList<Equipment> maintenanceDominationBoxList;

    // Game  page private variables 
    private ObservableList<Equipment> gameEquipmentList;

    //Database references for Equipment
    private EquipmentDAO equipmentDAO;

    //Database references for Maintenance
    private MaintenanceDAO maintenanceDAO;

    //Database references for Game
    private GameDAO gameDAO;

    //Database reference for userName and password
    private AdminAccessDAO adminAccessDAO;

// Define table for charging view
    @FXML
    TableView<Equipment> equipmentTableID;
    @FXML
    TableColumn<Equipment, String> equipmentID;
    @FXML
    TableColumn<Equipment, String> dateCharged;

    // Define table for game view
    @FXML
    TableView<Equipment> gameTableID;
    @FXML
    TableColumn<Equipment, String> gameEquipmentID;
    @FXML
    TableColumn<Equipment, String> gamedateCharged;

    // Define table for maintenance view
    @FXML
    TableView<Equipment> maintenanceTableID;
    @FXML
    TableColumn<Equipment, String> maintenanceEquipmentID;
    @FXML
    TableColumn<Equipment, String> maintenancedateCharged;

    /*@FXML
    TableColumn<Equipment, String> damageDescription;
    @FXML
    TableColumn<Equipment, Double> dateBroken;
    @FXML
    TableColumn<Equipment, Double> dateRepaired;
     */
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

    //Variables for pushing update button and open "Are you sure?" Pop up window
    @FXML
    private Button updateButton;

    //Variables for send to maintenance pop up
    @FXML
    private Button sendToMaintenanceButton;

    //Variables for charging/game buttons
    @FXML
    private Button gameButton;

    @FXML
    private Button chargingButton;

    // Initializes the controller class.
    // Creating new database objects
    // Loading database for charging view
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        adminAccessDAO = new AdminAccessDAO(new SqliteConnectionImpl());
        equipmentDAO = new EquipmentDAO(new SqliteConnectionImpl());
        maintenanceDAO = new MaintenanceDAO(new SqliteConnectionImpl());
        gameDAO = new GameDAO(new SqliteConnectionImpl());
        loadDatabaseCharging();
    }

    // Created method for when charging button is pushed
    @FXML
    public void chargingButtonPushed(ActionEvent event) throws IOException {
        changeSceneToChargingView();
        loadDatabaseCharging();
    }

    // Created method for changing scene to charging view
    private void changeSceneToChargingView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("p7_group3/LaserTag/view/ChargingView.fxml"));
        loader.setController(this);
        Parent Parent = (Parent) loader.load();
        Scene Scene = new Scene(Parent);
        mainApplication.primaryStage.setScene(Scene);
    }

    //Loading database in charging View
    private void loadDatabaseCharging() {
        equipmentList = FXCollections.observableArrayList();
        equipmentList.addAll(equipmentDAO.GetAllEquipment());

        equipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        dateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        equipmentTableID.setItems(null);
        equipmentTableID.setItems(equipmentList);
    }

    //Changing scene to game scene and loading database in game View 
    @FXML
    public void gameButtonPushed(ActionEvent event) throws IOException {
        changeSceneToGameView();
        loadingDatabaseGame();
    }

    // Created method for changing scene to game view
    public void changeSceneToGameView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("p7_group3/LaserTag/view/GameView.fxml"));
        loader.setController(this);
        Parent parent = (Parent) loader.load();
        Scene scene = new Scene(parent);
        mainApplication.primaryStage.setScene(scene);
    }

    //Loading database in game View
    public void loadingDatabaseGame() {
        gameEquipmentList = FXCollections.observableArrayList();
        gameEquipmentList.addAll(gameDAO.GetGameEquipment());

        gameEquipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        gamedateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        equipmentTableID.setItems(null);
        equipmentTableID.setItems(gameEquipmentList);
    }

    // Method for pushing maintenance button and login view
    public void openLoginView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("p7_group3/LaserTag/view/LoginView.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.getIcons().add(new Image("pictures/glove.png"));
        stage.setTitle("Laser-tag application");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        LoginViewController loginViewController = loader.getController();
        loginViewController.setTableViewController(this);
    }

    //Changing scene to maintenance scene and loading database in maintenance view 
    public void loginButtonPushed(ActionEvent Event) throws IOException {
        changeSceneToMaintenanceView();
        loadDatabaseMaintenance();
    }

    //Changing scene to maintenance scene
    public void changeSceneToMaintenanceView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("p7_group3/LaserTag/view/MaintenanceView.fxml"));
        loader.setController(this);
        Parent parent = (Parent) loader.load();
        Scene scene = new Scene(parent);
        mainApplication.primaryStage.setScene(scene);
    }

    //Loading database in maintenance view
    @FXML
    public void loadDatabaseMaintenance() {
        maintenanceEquipmentList = FXCollections.observableArrayList();
        maintenanceEquipmentList.addAll(maintenanceDAO.GetBrokenEquipment());

        maintenanceEquipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        maintenancedateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        maintenanceTableID.setItems(null);
        maintenanceTableID.setItems(maintenanceEquipmentList);
    }

    //Method for showing and assigning names to drop down menu
    public void dropDownMenu(ActionEvent event) throws IOException {
        MenuButton m = new MenuButton("selectEquipment");
        m.getItems().addAll(new MenuItem("allEquipment"), new MenuItem("guns"), new MenuItem("medicalBoxes"), new MenuItem("gameControllers"), new MenuItem("dominationBoxes"));

    }
    
    @FXML
    public void showAllEquipment(ActionEvent event) throws IOException {
        equipmentList = FXCollections.observableArrayList();
        equipmentList.addAll(equipmentDAO.GetAllEquipment());

        equipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        dateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        equipmentTableID.setItems(null);
        equipmentTableID.setItems(equipmentList);
    }
    
     @FXML
    public void showAllEquipmentMaintenance(ActionEvent even) throws IOException {
        maintenanceEquipmentList = FXCollections.observableArrayList();
        maintenanceEquipmentList.addAll(maintenanceDAO.GetBrokenEquipment());

        maintenanceEquipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        maintenancedateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        maintenanceTableID.setItems(null);
        maintenanceTableID.setItems(maintenanceEquipmentList);
    }

    //Method for loading GUNS ONLY IN MAIN SCREEN
    @FXML
    public void showGuns(ActionEvent event) throws IOException {
        gunList = FXCollections.observableArrayList();
        gunList.addAll(equipmentDAO.GetAllGuns());

        equipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        dateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        equipmentTableID.setItems(null);
        equipmentTableID.setItems(gunList);
    }

    //Method for loading GUNS ONLY IN MAINTENANCE
    @FXML
    public void showGunsMaintenance(ActionEvent event) throws IOException {
        maintenanceGunList = FXCollections.observableArrayList();
        maintenanceGunList.addAll(equipmentDAO.GetAllGuns());

        maintenanceEquipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        maintenancedateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        maintenanceTableID.setItems(null);
        maintenanceTableID.setItems(maintenanceGunList);
    }

    //Method for loading MEDIX BOXES ONLY IN MAIN SCREEN
    @FXML
    public void showMedicBoxes(ActionEvent event) throws IOException {
        medicBoxList = FXCollections.observableArrayList();
        medicBoxList.addAll(equipmentDAO.GetAllMedicBoxes());

        equipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        dateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        equipmentTableID.setItems(null);
        equipmentTableID.setItems(medicBoxList);
    }

    //Method for loading MEDIC BOXES ONLY IN MAINTENANCE
    @FXML
    public void showMedicBoxesMaintenance(ActionEvent event) throws IOException {
        maintenanceMedicBoxList = FXCollections.observableArrayList();
        maintenanceMedicBoxList.addAll(equipmentDAO.GetAllMedicBoxes());

        maintenanceEquipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        maintenancedateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        maintenanceTableID.setItems(null);
        maintenanceTableID.setItems(maintenanceMedicBoxList);
    }

    //Method for loading GAME CONTROLLER ONLY IN MAIN SCREEN
    @FXML
    public void showGameController(ActionEvent event) throws IOException {
        gameControllerList = FXCollections.observableArrayList();
        gameControllerList.addAll(equipmentDAO.GetAllGameControllers());

        equipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        dateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        equipmentTableID.setItems(null);
        equipmentTableID.setItems(gameControllerList);
    }

    //Method for loading GAME CONTROLLER ONLY IN MAINTENANCE
    @FXML
    public void showGameControllerMaintenance(ActionEvent event) throws IOException {
        maintenanceGameControllerList = FXCollections.observableArrayList();
        maintenanceGameControllerList.addAll(equipmentDAO.GetAllGameControllers());

        maintenanceEquipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        maintenancedateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        maintenanceTableID.setItems(null);
        maintenanceTableID.setItems(maintenanceGameControllerList);
    }

    //Method for loading DOMINATION BOXES ONLY IN MAIN SCREEN
    @FXML
    public void showDominationBoxes(ActionEvent event) throws IOException {
        dominationBoxList = FXCollections.observableArrayList();
        dominationBoxList.addAll(equipmentDAO.GetAllDominationBoxes());

        equipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        dateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        equipmentTableID.setItems(null);
        equipmentTableID.setItems(dominationBoxList);
    }

    //Method for loading DOMINATION BOXES ONLY IN MAINTENANCE
    @FXML
    public void showDominationMaintenance(ActionEvent event) throws IOException {
        maintenanceDominationBoxList = FXCollections.observableArrayList();
        maintenanceDominationBoxList.addAll(equipmentDAO.GetAllDominationBoxes());

        maintenanceEquipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        maintenancedateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        maintenanceTableID.setItems(null);
        maintenanceTableID.setItems(maintenanceDominationBoxList);
    }

    //Method for "Are you sure?" pop up window
    public void areYouSurePopUp(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("p7_group3/LaserTag/view/AreYouSureView.fxml"));
        Scene sureScene = new Scene(root);
        stage.getIcons().add(new Image("pictures/glove.png"));
        stage.setTitle("Laser-tag application");
        stage.setScene(sureScene);
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
}
