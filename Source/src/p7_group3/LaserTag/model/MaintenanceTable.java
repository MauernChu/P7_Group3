package p7_group3.LaserTag.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Table class
 *
 * @author Jannik Boldsen
 */
public class MaintenanceTable {
    private final SimpleStringProperty damID;
    private final SimpleStringProperty desID;

    
    public MaintenanceTable(String wID, String xID){
        this.damID = new SimpleStringProperty(wID);
        this.desID = new SimpleStringProperty(xID);
  }
    
   // Getters and setters 
    public String getDamID(){
        return damID.get();
    }
    
    public void setDamID(String wID){
        damID.set(wID);
    }
    
 
    
   // Getters and setters 
    public String getDesID(){
        return desID.get();
    }
    
    public void setDesID(String xID){
        desID.set(xID);
    }
    
    
}
