package p7_group3.LaserTag;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import p7_group3.LaserTag.controller.TableViewController;

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
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/TableView.fxml"));
        loader.setController(new TableViewController());
        Parent root = (Parent) loader.load();
        Scene homeScene = new Scene(root);
        primaryStage.getIcons().add(new Image("pictures/glove.png"));
        primaryStage.setTitle("Laser-tag application");
        primaryStage.setScene(homeScene);
        primaryStage.show();
        
        //Set the main application reference in the tableViewController
        TableViewController tableViewController = loader.getController();
        tableViewController.setMainApp(this);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
