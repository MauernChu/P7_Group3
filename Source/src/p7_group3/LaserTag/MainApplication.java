package p7_group3.LaserTag;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import p7_group3.LaserTag.controller.TableViewController;
import p7_group3.LaserTag.controller.TableViewControllerWithModels;

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
        loader.setController(new TableViewController()); //making instance programmaticaaly (with out scenebuilder)
        //making instance programmaticaaly (with out scenebuilder)
        //because we know that we need this (the same) instance in multiple views (when we go from maintenance and back
        //to tableview)
        Parent root = (Parent) loader.load();
        Scene homeScene = new Scene(root);
        primaryStage.getIcons().add(new Image("pictures/glove.png"));
        primaryStage.setTitle("Laser-tag application");
        primaryStage.setScene(homeScene);
        primaryStage.show();
        
        //Set the main application reference in the tableViewController
        TableViewController tableViewController = loader.getController();
        tableViewController.setMainApp(this);
        //TableViewControllerWithModels tableViewController = loader.getController();
        //tableViewController.setMainApp(this);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
