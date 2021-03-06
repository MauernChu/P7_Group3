package p7_group3.LaserTag.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import p7_group3.LaserTag.database.AdminAccessDAO;
import p7_group3.LaserTag.database.SqliteConnectionImpl;

public class LoginViewController implements Initializable {

    ChargingViewController chargingViewController;
    AdminAccessDAO adminAccessDao = new AdminAccessDAO(new SqliteConnectionImpl());
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //Creates variable connected to Scenebuilder through ID
    @FXML
    private Label lblStatus;

    @FXML
    public Label lblTitel;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPassword;

    public void loginButtonPushed(ActionEvent event) throws IOException {
        boolean canAccess = adminAccessDao.CanAccess(txtUserName.getText(), txtPassword.getText());
        
        if (canAccess == true) { //checks if the password and username matches UserNameDb and PasswordDb
            lblStatus.setText(""); //Connects to lblStatus button, and states the given text
            ((Node)(event.getSource())).getScene().getWindow().hide();
            chargingViewController.loginButtonPushed(event);
        } else {
            lblStatus.setText("Login failed");
        }
    }
    
    public void setTableViewController(ChargingViewController tableViewController) {
        this.chargingViewController = tableViewController;
    }
}
