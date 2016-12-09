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

public class MaintenanceDAO {
    
    private final DbConnection dbConnection;

    public MaintenanceDAO(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public ArrayList<Equipment> GetBrokenEquipment() {
        final String sql = "SELECT * FROM MainDatabase WHERE Maintenance LIKE '%null%' order by TimePutToChargeNumeric asc";
        
        return GetMaintenanceEquipment(sql);
    }
    
    public ArrayList<Equipment> GetAllBrokenGuns() {
        final String sql = "SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%GU%' AND Maintenance LIKE '%null%'";
        
        return GetMaintenanceEquipment(sql);
    }
    
    public ArrayList<Equipment> GetAllBrokenMedicBoxes() {
        final String sql = "SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%MED%' AND Maintenance LIKE '%null%'";
        
        return GetMaintenanceEquipment(sql);
    }
    
    public ArrayList<Equipment> GetAllBrokenGameControllers() {
        final String sql = ("SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%CO%' AND Maintenance LIKE '%null%'");
        
        return GetMaintenanceEquipment(sql); 
    }
    
    public ArrayList<Equipment> GetAllBrokenDominationBoxes() {
        final String sql = ("SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%DO%' AND Maintenance LIKE '%null%'");
        
        return GetMaintenanceEquipment(sql); 
    }
     
    private ArrayList<Equipment> GetMaintenanceEquipment(String sql) { 
        ArrayList<Equipment> equipments = new ArrayList<>();

        Statement stmt = null;

        try {
            stmt = dbConnection.createConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ChargingStatus chargingStatus = new ChargingStatus(rs.getDate(4));
                Maintenance maintenance = new Maintenance (rs.getString(7), rs.getDate(11), rs.getString(8), rs.getString(12));
                equipments.add(new Equipment(rs.getInt(1), rs.getString(2), chargingStatus, maintenance, rs.getBoolean(9), rs.getBoolean(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return equipments;
    }

    
    //
        public void GetProblemDescription(String damageDescription, String nameDiscoveredDamage, String problem) { 
        Statement stmt = null;
        System.out.println(damageDescription);
        try {
            stmt = dbConnection.createConnection().createStatement();
           int rs = stmt.executeUpdate("UPDATE MainDatabase SET DamageDescription = '"+damageDescription+"'");
           int rs1 = stmt.executeUpdate("UPDATE MainDatabase SET IdentifierOfProblem = '"+nameDiscoveredDamage+"'");
           int rs2 = stmt.executeUpdate("UPDATE MainDatabase SET DamageDefinition = '"+problem+"'");
           int rs3 = stmt.executeUpdate("UPDATE MainDatabase SET DateBroken = strftime('%J', 'NOW', 'localtime')");
 
        } catch (SQLException ex) {
            Logger.getLogger(EquipmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    

}
    
    
}

