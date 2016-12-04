package p7_group3.LaserTag;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Mette
 */
public class MainApplication extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent homePage = FXMLLoader.load(getClass().getResource("view/FXML.fxml"));
        Scene homeScene = new Scene(homePage);
        stage.getIcons().add(new Image("pictures/glove.png"));
        stage.setTitle("Laser-tag application");
        stage.setScene(homeScene);
        stage.show();
        Stage stage1 = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("p7_group3/LaserTag/view/FirstPopUpView.fxml"));
        Scene scene = new Scene(root);
        stage1.getIcons().add(new Image("pictures/glove.png"));
        stage1.setTitle("Laser-tag application");
        stage1.setScene(scene);
        stage1.show();
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
