/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p7_group3.LaserTag.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class EquipmentAccessDAO {

    public Connection Connect() {
        try {
            //Your database url string,ensure it is correct
          
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:LaserTag.db");
            return conn;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EquipmentAccessDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
