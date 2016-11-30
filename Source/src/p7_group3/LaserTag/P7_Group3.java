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
public class P7_Group3 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent homePage = FXMLLoader.load(getClass().getResource("view/FXML.fxml"));
        Scene homeScene = new Scene(homePage);
        stage.getIcons().add(new Image("pictures/glove.png"));
        stage.setTitle("Laser-tag application");
        stage.setScene(homeScene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
