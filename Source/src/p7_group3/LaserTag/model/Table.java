package p7_group3.LaserTag.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Table class
 *
 * @author Jannik Boldsen
 */
public class Table {
    private final SimpleStringProperty rID;
    private final SimpleDoubleProperty dID;

    
    public Table(String uID, double vID){
        this.rID = new SimpleStringProperty(uID);
        this.dID = new SimpleDoubleProperty(vID);
  }
    
   // Getters and setters 
    public String getRID(){
        return rID.get();
    }
    
    public void setRID(String uID){
        rID.set(uID);
    }
    
 
    
   // Getters and setters 
    public double getDID(){
        return dID.get();
    }
    
    public void setDID(double vID){
        dID.set(vID);
    }
    
    
}
