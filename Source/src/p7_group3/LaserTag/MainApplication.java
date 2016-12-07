package p7_group3.LaserTag;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import p7_group3.LaserTag.controller.ChargingViewController;


/**
 *
 * @author Mette
 */
public class MainApplication extends Application {
    
    public MainApplication mainApplication;
    public Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/ChargingView.fxml"));
        loader.setController(new ChargingViewController()); //Making an object of the ChargingViewController for the Charging/Game/Maintenance view manually.
        //We use the same controller object for Charging/Game/Maintenance view and thats why we make the controller manually.
        Parent root = (Parent) loader.load();
        Scene homeScene = new Scene(root);
        primaryStage.getIcons().add(new Image("pictures/glove.png"));
        primaryStage.setTitle("Laser-tag application");
        primaryStage.setScene(homeScene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        //Set the main application reference in the tableViewController
        ChargingViewController chargingViewController = loader.getController();
        chargingViewController.setMainApp(this);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
