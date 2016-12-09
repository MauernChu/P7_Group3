package p7_group3.LaserTag.controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
//import java.util.Date;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
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

    //
    private ObservableList<Equipment> gameEquipmentList;
    private ObservableList<Equipment> gameGunList;
    private ObservableList<Equipment> gameMedicBoxList;
    private ObservableList<Equipment> gameGameControllerList;
    private ObservableList<Equipment> gameDominationBoxList;

    // Maintenance page private variables 
    private ObservableList<Equipment> maintenanceEquipmentList;
    private ObservableList<Equipment> maintenanceGunList;
    private ObservableList<Equipment> maintenanceMedicBoxList;
    private ObservableList<Equipment> maintenanceGameControllerList;
    private ObservableList<Equipment> maintenanceDominationBoxList;

    // Game  page private variables 
    //Database references for Equipment
    private EquipmentDAO equipmentDAO;

    //Database references for Maintenance
    private MaintenanceDAO maintenanceDAO;

    //Database references for Game
    private GameDAO gameDAO;

    //Database reference for userName and password
    public AdminAccessDAO adminAccessDAO;

    // Variables for storing row colors 
    Equipment color;

    // Define table for charging view
    @FXML
    TableView<Equipment> equipmentTableID;
    @FXML
    TableColumn<Equipment, String> equipmentID;
    @FXML
    TableColumn<Equipment, String> dateCharged;
    @FXML
    TableColumn<Equipment, Boolean> checkbox;

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
    @FXML
    TableColumn<Equipment, String> maintenanceDamageDescription;
    @FXML
    TableColumn<Equipment, String> maintenanceDateBroken;
    @FXML
    TableColumn<Equipment, String> maintenanceNameDiscoveredDamage;
    @FXML
    TableColumn<Equipment, String> maintenanceDamageDefinition;

    /*@FXML
    TableColumn<Equipment, String> damageDescription;
    @FXML
    TableColumn<Equipment, Double> dateBroken;
    @FXML
    TableColumn<Equipment, Double> dateRepaired;
     */
    //Variables for Drop Down Menu SHARED BETWEEN ALL PAGES
    @FXML
    public MenuButton selectEquipment;
    @FXML
    public MenuItem allEquipment;
    @FXML
    public MenuItem guns;
    @FXML
    public MenuItem medicalBoxes;
    @FXML
    public MenuItem gameControllers;
    @FXML
    public MenuItem dominationBoxes;

    //Variables for pushing update button and open "Are you sure?" Pop up window
    @FXML
    public Button updateButton;

    //Variables for send to maintenance pop up
    @FXML
    public Button sendToMaintenanceButton;

    //Variables for charging/game buttons
    @FXML
    public Button gameButton;

    @FXML
    public Button chargingButton;

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
    public void loadDatabaseCharging() {
        equipmentList = FXCollections.observableArrayList();
        equipmentList.addAll(equipmentDAO.GetAllEquipment());

        equipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        dateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));
        checkbox.setCellValueFactory(cellData -> cellData.getValue().checkbox);

        checkbox.setCellFactory(new Callback<TableColumn<Equipment, Boolean>, TableCell<Equipment, Boolean>>() {

            public TableCell<Equipment, Boolean> call(TableColumn<Equipment, Boolean> p) {
                return new CheckBoxTableCell<Equipment, Boolean>();
            }

        });

        equipmentTableID.setItems(null);

        equipmentTableID.setRowFactory(new Callback<TableView<Equipment>, TableRow<Equipment>>() {
            @Override
            public TableRow<Equipment> call(TableView<Equipment> tableTableView) {
                return new TableRowColorFormat();
            }
        });

        equipmentTableID.setItems(equipmentList);
        updateColor();

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

        gameTableID.setItems(null);
        gameTableID.setItems(gameEquipmentList);

        updateColorGame();
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
        maintenancedateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd").format(cellData.getValue().chargingStatus.getDateCharged())));
        maintenanceDateBroken.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd").format(cellData.getValue().maintenance.getDateBroken())));
        maintenanceNameDiscoveredDamage.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().maintenance.getNameDiscoverDamage())));
        maintenanceDamageDefinition.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().maintenance.getDamageDefinition())));

        maintenanceTableID.setItems(null);
        maintenanceTableID.setItems(maintenanceEquipmentList);
    }

    //Method for showing and assigning names to drop down menu
    public void dropDownMenu(ActionEvent event) throws IOException {
        MenuButton m = new MenuButton("selectEquipment");
        m.getItems().addAll(new MenuItem("allEquipment"), new MenuItem("guns"), new MenuItem("medicalBoxes"), new MenuItem("gameControllers"), new MenuItem("dominationBoxes"));
        MenuItem menu = (MenuItem) event.getSource();
        selectEquipment.setText(menu.getText());
    }

    @FXML
    public void showAllEquipment(ActionEvent event) throws IOException {
        equipmentList = FXCollections.observableArrayList();
        equipmentList.addAll(equipmentDAO.GetAllEquipment());

        equipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        dateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        equipmentTableID.setItems(null);
        equipmentTableID.setItems(equipmentList);

        MenuItem menu = (MenuItem) event.getSource();
        selectEquipment.setText(menu.getText());
    }

    @FXML
    public void showAllEquipmentGame(ActionEvent event) throws IOException {
        gameEquipmentList = FXCollections.observableArrayList();
        gameEquipmentList.addAll(gameDAO.GetGameEquipment());

        gameEquipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        gamedateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        gameTableID.setItems(null);
        gameTableID.setItems(gameEquipmentList);

        MenuItem menu = (MenuItem) event.getSource();
        selectEquipment.setText(menu.getText());
    }

    @FXML
    public void showAllEquipmentMaintenance(ActionEvent even) throws IOException {
        maintenanceEquipmentList = FXCollections.observableArrayList();
        maintenanceEquipmentList.addAll(maintenanceDAO.GetBrokenEquipment());

        maintenanceEquipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        maintenancedateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        maintenanceTableID.setItems(null);
        maintenanceTableID.setItems(maintenanceEquipmentList);

        MenuItem menu = (MenuItem) even.getSource();
        selectEquipment.setText(menu.getText());
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

        MenuItem menu = (MenuItem) event.getSource();
        selectEquipment.setText(menu.getText());
    }

    @FXML
    public void showGunsGame(ActionEvent event) throws IOException {
        gameGunList = FXCollections.observableArrayList();
        gameGunList.addAll(gameDAO.GetAllGameGuns());

        gameEquipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        gamedateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        gameTableID.setItems(null);
        gameTableID.setItems(gameGunList);

        MenuItem menu = (MenuItem) event.getSource();
        selectEquipment.setText(menu.getText());
    }

    //Method for loading GUNS ONLY IN MAINTENANCE
    @FXML
    public void showGunsMaintenance(ActionEvent event) throws IOException {
        maintenanceGunList = FXCollections.observableArrayList();
        maintenanceGunList.addAll(maintenanceDAO.GetAllBrokenGuns());

        maintenanceEquipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        maintenancedateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        maintenanceTableID.setItems(null);
        maintenanceTableID.setItems(maintenanceGunList);

        MenuItem menu = (MenuItem) event.getSource();
        selectEquipment.setText(menu.getText());
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

        MenuItem menu = (MenuItem) event.getSource();
        selectEquipment.setText(menu.getText());
    }

    @FXML
    public void showMedicBoxesGame(ActionEvent event) throws IOException {
        gameMedicBoxList = FXCollections.observableArrayList();
        gameMedicBoxList.addAll(gameDAO.GetAllGameMedicBoxes());

        gameEquipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        gamedateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        gameTableID.setItems(null);
        gameTableID.setItems(gameMedicBoxList);

        MenuItem menu = (MenuItem) event.getSource();
        selectEquipment.setText(menu.getText());
    }

    //Method for loading MEDIC BOXES ONLY IN MAINTENANCE
    @FXML
    public void showMedicBoxesMaintenance(ActionEvent event) throws IOException {
        maintenanceMedicBoxList = FXCollections.observableArrayList();
        maintenanceMedicBoxList.addAll(maintenanceDAO.GetAllBrokenMedicBoxes());

        maintenanceEquipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        maintenancedateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        maintenanceTableID.setItems(null);
        maintenanceTableID.setItems(maintenanceMedicBoxList);

        MenuItem menu = (MenuItem) event.getSource();
        selectEquipment.setText(menu.getText());
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

        MenuItem menu = (MenuItem) event.getSource();
        selectEquipment.setText(menu.getText());
    }

    @FXML
    public void showGameControllerGame(ActionEvent event) throws IOException {
        gameGameControllerList = FXCollections.observableArrayList();
        gameGameControllerList.addAll(gameDAO.GetAllGameControllersGame());

        gameEquipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        gamedateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        gameTableID.setItems(null);
        gameTableID.setItems(gameGameControllerList);

        MenuItem menu = (MenuItem) event.getSource();
        selectEquipment.setText(menu.getText());
    }

    //Method for loading GAME CONTROLLER ONLY IN MAINTENANCE
    @FXML
    public void showGameControllerMaintenance(ActionEvent event) throws IOException {
        maintenanceGameControllerList = FXCollections.observableArrayList();
        maintenanceGameControllerList.addAll(maintenanceDAO.GetAllBrokenGameControllers());

        maintenanceEquipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        maintenancedateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        maintenanceTableID.setItems(null);
        maintenanceTableID.setItems(maintenanceGameControllerList);

        MenuItem menu = (MenuItem) event.getSource();
        selectEquipment.setText(menu.getText());
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

        MenuItem menu = (MenuItem) event.getSource();
        selectEquipment.setText(menu.getText());
    }

    @FXML
    public void showDominationBoxesGame(ActionEvent event) throws IOException {
        gameDominationBoxList = FXCollections.observableArrayList();
        gameDominationBoxList.addAll(gameDAO.GetAllGameDominationBoxes());

        gameEquipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        gamedateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        gameTableID.setItems(null);
        gameTableID.setItems(gameDominationBoxList);

        MenuItem menu = (MenuItem) event.getSource();
        selectEquipment.setText(menu.getText());
    }

    //Method for loading DOMINATION BOXES ONLY IN MAINTENANCE
    @FXML
    public void showDominationMaintenance(ActionEvent event) throws IOException {
        maintenanceDominationBoxList = FXCollections.observableArrayList();
        maintenanceDominationBoxList.addAll(maintenanceDAO.GetAllBrokenDominationBoxes());

        maintenanceEquipmentID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().name)));
        maintenancedateCharged.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().chargingStatus.getDateCharged())));

        maintenanceTableID.setItems(null);
        maintenanceTableID.setItems(maintenanceDominationBoxList);

        MenuItem menu = (MenuItem) event.getSource();
        selectEquipment.setText(menu.getText());
    }

    //Method for "Are you sure?" pop up window
    public void areYouSurePopUp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("p7_group3/LaserTag/view/AreYouSureView.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.getIcons().add(new Image("pictures/glove.png"));
        stage.setTitle("Laser-tag application");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        AreYouSureViewController areYouSureViewController = loader.getController();
        areYouSureViewController.setChargingViewController(this);
    }

    
    //Method for sendToMaintenance pop up window
    public void sendToMaintenancePopUp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("p7_group3/LaserTag/view/MaintenancePopUpView.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.getIcons().add(new Image("pictures/glove.png"));
        stage.setTitle("Laser-tag application");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        MaintenancePopUpViewController maintenancePopUpViewController = loader.getController();
        maintenancePopUpViewController.setChargingViewController(this);
    }
    
    //Method for AddEquipment pop up window
    public void addEquipment(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("p7_group3/LaserTag/view/AddEquipmentView.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.getIcons().add(new Image("pictures/glove.png"));
        stage.setTitle("Laser-tag application");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        AddEquipmentViewController addEquipmentViewController = loader.getController();
        addEquipmentViewController.setChargingViewController(this);
    }

    public void updateColor() {
        equipmentTableID.setRowFactory(new Callback<TableView<Equipment>, TableRow<Equipment>>() {
            @Override
            public TableRow<Equipment> call(TableView<Equipment> tableTableView) {
                return new TableRowColorFormat();
            }
        });
    }

    private class TableRowColorFormat extends TableRow {

        @Override
        protected void updateItem(Object o, boolean b) {
            super.updateItem(o, b);

            if (o == null) {
                getStyleClass().remove("highlightedRow");
                return;
            }
            if (o.getClass() == Equipment.class) {
                Equipment Equipment = (Equipment) o;

                getStyleClass().remove("highlightedRow");
                long DAY_IN_MS = 1000 * 60 * 60 * 24;
                Date expireDate = new Date(System.currentTimeMillis() - (1 * DAY_IN_MS));
                if (Equipment.getChargingStatus().getDateCharged().before(expireDate)) {

                    getStyleClass().add("highlightedRow");

                }
            }
            return;

        }
    }

    public void updateColorGame() {
        gameTableID.setRowFactory(new Callback<TableView<Equipment>, TableRow<Equipment>>() {
            @Override
            public TableRow<Equipment> call(TableView<Equipment> tableTableView) {
                return new TableRowColorGameFormat();
            }
        });
    }

    private class TableRowColorGameFormat extends TableRow {

        @Override
        protected void updateItem(Object o, boolean b) {
            super.updateItem(o, b);

            if (o == null) {
                getStyleClass().remove("highlightedRowGame");
                return;
            }
            if (o.getClass() == Equipment.class) {
                Equipment Equipment = (Equipment) o;

                getStyleClass().remove("highlightedRowGame");
                long DAY_IN_MS = 1000 * 60 * 60 * 24;
                Date expireDate = new Date(System.currentTimeMillis() - (2 * DAY_IN_MS));
                if (Equipment.getChargingStatus().getDateCharged().after(expireDate)) {

                    getStyleClass().add("highlightedRowGame");

                }
            }
            return;
        }
    }

    //CheckBoxTableCell for creating a CheckBox in a table cell
    public static class CheckBoxTableCell<S, T> extends TableCell<S, T> {

        private final CheckBox checkBox;
        private ObservableValue<T> ov;

        public CheckBoxTableCell() {
            this.checkBox = new CheckBox();
            this.checkBox.setAlignment(Pos.CENTER);

            // Equipment.class.cast(ov);
            // TableRow tr = this.getTableRow();
            //int index = tr.getIndex();
            //int index = 2;
            // this.checkBox.selectedProperty().addListener(new RowCheckBoxChangeListener(index));
            setAlignment(Pos.CENTER);
            setGraphic(checkBox);

        }

        @Override
        public void updateItem(T item, boolean empty) {
            // call super class update method
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                setGraphic(checkBox);
                if (ov instanceof BooleanProperty) {
                    checkBox.selectedProperty().unbindBidirectional((BooleanProperty) ov);
                }
                ov = getTableColumn().getCellObservableValue(getIndex());
                if (ov instanceof BooleanProperty) {
                    checkBox.selectedProperty().bindBidirectional((BooleanProperty) ov);
                }
            }

        }
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
