/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p7_group3.LaserTag.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import p7_group3.LaserTag.model.ChargingStatus;
import p7_group3.LaserTag.model.Equipment;
import p7_group3.LaserTag.model.Maintenance;

/**
 *
 * @author Mette
 */
public class EquipmentDAO {

    private final DbConnection dbConnection;

    public EquipmentDAO(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public ArrayList<Equipment> GetAllEquipment() {
        final String sql = "SELECT * FROM MainDatabase order by TimePutToChargeNumeric asc";
        
        return GetBySqlSearchString(sql);
    }
    
    public ArrayList<Equipment> GetAllGuns() {
        final String sql = "SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%GU%'";
        
        return GetBySqlSearchString(sql);
    }
    
    public ArrayList<Equipment> GetAllMedicBoxes() {
        final String sql = "SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%MED%'";
        
        return GetBySqlSearchString(sql);
    }
    
    public ArrayList<Equipment> GetAllGameControllers() {
        final String sql = ("SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%CO%'");
        
        return GetBySqlSearchString(sql); 
    }
    
    public ArrayList<Equipment> GetAllDominationBoxes() {
        final String sql = ("SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%DO%'");
        
        return GetBySqlSearchString(sql); 
    }
     
    private ArrayList<Equipment> GetBySqlSearchString(String sql) { 
        ArrayList<Equipment> equipments = new ArrayList<Equipment>();

        Statement stmt = null;

        try {
            stmt = dbConnection.createConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ChargingStatus chargingStatus = new ChargingStatus(rs.getDate(4));
                Maintenance maintenance = null;
                equipments.add(new Equipment(rs.getInt(1), rs.getString(2), chargingStatus, maintenance));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return equipments;
    }
    
    

}

