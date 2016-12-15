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


public class GameDAO {
        private final DbConnection dbConnection;

    public GameDAO(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
    
    public ArrayList<Equipment> GetGameEquipment() {
        final String sql = "SELECT * FROM MainDatabase WHERE Maintenance LIKE '%1%' order by TimePutToChargeNumeric desc";

        return GetGameEquipment(sql);
    }

    public ArrayList<Equipment> GetAllGameGuns() {
        final String sql = "SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%GU%' AND Maintenance LIKE '%1%' order by TimePutToChargeNumeric desc";

        return GetGameEquipment(sql);
    }

    public ArrayList<Equipment> GetAllGameMedicBoxes() {
        final String sql = "SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%MED%' AND Maintenance LIKE '%1%' order by TimePutToChargeNumeric desc";

        return GetGameEquipment(sql);
    }

    public ArrayList<Equipment> GetAllGameControllersGame() {
        final String sql = ("SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%CO%' AND Maintenance LIKE '%1%' order by TimePutToChargeNumeric desc");

        return GetGameEquipment(sql);
    }

    public ArrayList<Equipment> GetAllGameDominationBoxes() {
        final String sql = ("SELECT * FROM MainDatabase WHERE EquipmentID LIKE '%DO%' AND Maintenance LIKE '%1%'order by TimePutToChargeNumeric desc");

        return GetGameEquipment(sql);
    }

    public ArrayList<Equipment> GetGameEquipment(String sql) {

        ArrayList<Equipment> gameEquipment = new ArrayList<>();

        Statement stmt = null;

        try {
            stmt = dbConnection.createConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ChargingStatus chargingStatus = new ChargingStatus(rs.getDate(4));
                Maintenance maintenance = null;
                gameEquipment.add(new Equipment(rs.getString(1),rs.getString(2), chargingStatus, maintenance, rs.getBoolean(9), rs.getBoolean(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return gameEquipment;
    }
     
}
