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

public class EquipmentDAO {

    private final DbConnection dbConnection;

    public EquipmentDAO(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public ArrayList<Equipment> GetAllEquipment() {
        final String sql = "SELECT * FROM MainDatabase WHERE Maintenance LIKE '%1%' order by TimePutToChargeNumeric asc";

        return GetChargingEquipment(sql);
    }

    public ArrayList<Equipment> GetAllGuns() {
        final String sql = "SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%GU%' AND Maintenance LIKE '%1%' order by TimePutToChargeNumeric asc";

        return GetChargingEquipment(sql);
    }

    public ArrayList<Equipment> GetAllMedicBoxes() {
        final String sql = "SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%MED%' AND Maintenance LIKE '%1%' order by TimePutToChargeNumeric asc";

        return GetChargingEquipment(sql);
    }

    public ArrayList<Equipment> GetAllGameControllers() {
        final String sql = ("SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%CO%' AND Maintenance LIKE '%1%' order by TimePutToChargeNumeric asc");

        return GetChargingEquipment(sql);
    }

    public ArrayList<Equipment> GetAllDominationBoxes() {
        final String sql = ("SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%DO%' AND Maintenance LIKE '%1%'order by TimePutToChargeNumeric asc");

        return GetChargingEquipment(sql);
    }

    private ArrayList<Equipment> GetChargingEquipment(String sql) {
        ArrayList<Equipment> equipments = new ArrayList<>();

        Statement stmt = null;

        try {
            stmt = dbConnection.createConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ChargingStatus chargingStatus = new ChargingStatus(rs.getDate(4));
                Maintenance maintenance = null;
                equipments.add(new Equipment(rs.getString(1), rs.getString(2), chargingStatus, maintenance, rs.getBoolean(9), rs.getBoolean(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return equipments;
    }

    public void GetBySqlSearchRead() {
        Statement stmt = null;

        try {
            stmt = dbConnection.createConnection().createStatement();
            int rs = stmt.executeUpdate("UPDATE MainDatabase SET TimePutToChargeNumeric = strftime('%J', 'NOW', 'localtime')");

        } catch (SQLException ex) {
            Logger.getLogger(EquipmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
        public void UpdateCheckboxes() {
        Statement stmt = null;
        
        try {
            stmt = dbConnection.createConnection().createStatement();
            
            for (int i =0; i < Equipment.equipmentList.size();i++ ){
                String s = Equipment.equipmentList.get(i);
             int rs = stmt.executeUpdate("UPDATE MainDatabase SET TimePutToChargeNumeric = strftime('%J', 'NOW', "
                     + "'localtime') WHERE ID ='"+s+"' ");
            }

        } catch (SQLException ex) {
            Logger.getLogger(EquipmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
