package p7_group3.LaserTag.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AdminAccessDAO {
    
    private final DbConnection dbConnection;
    
    public AdminAccessDAO(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
    
    public boolean CanAccess(String userName, String password) {
        
        String userNameDb = null;
        String passwordDb = null;
        
        Statement stmt = null;
        try {
            stmt = dbConnection.createConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM AdminAccess");
            while ( rs.next() ) {
                userNameDb = rs.getString("UserName");
                passwordDb = rs.getString("Password");
            } } catch (SQLException ex) {
            Logger.getLogger(AdminAccessDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        if(userName.equals(userNameDb) && password.equals(passwordDb)) {
            return true;
        } else {
            return false;
        }
    }
}
