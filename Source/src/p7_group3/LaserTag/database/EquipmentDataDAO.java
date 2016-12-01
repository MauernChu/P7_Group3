/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p7_group3.LaserTag.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EquipmentDataDAO {
    
    //Here make we make a new object of the dbConnection, which is the interface to the SqliteConnectionImpl. 
    //the reason why we make an interface is that, then we can just change the sqliteConnectionImpl without changes
    //every piece of code where we use the connection.
    private final DbConnection dbConnection;
    
    //
    public EquipmentDataDAO(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
    
    //Make a new method that reads the data in the table "EquipmentData" in the database, 
    //and print the information in the console in netbeans. You can see this yet, because you
    //need to call this method, and in this case we are calling it in the FXMLController class.
     public void readEquipmentFromDatabase() {
        Statement stmt = null;
        try {
            stmt = dbConnection.createConnection().createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM EquipmentData;" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                String color  = rs.getString("color");
                System.out.println( "ID = " + id );
                System.out.println( "NAME = " + name );
                System.out.println( "COLOR = " + color );
                System.out.println();
            } } catch (SQLException ex) {
            Logger.getLogger(EquipmentDataDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
