package TableModel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Table class
 *
 * @author Jannik Boldsen
 */
public class Table {
    private final SimpleIntegerProperty rID;
    private final SimpleStringProperty rDateCharged;

    
    public Table(Integer uID, String uDateCharged){
        this.rID = new SimpleIntegerProperty (uID);
        this.rDateCharged = new SimpleStringProperty (uDateCharged);
  }
    
   // Getters and setters 
    public Integer getRID(){
        return rID.get();
    }
    
    public void setRID(Integer v){
        rID.set(v);
    }
   
    public String getRDateCharged(){
        return rDateCharged.get();
    }
    
    public void setRDateCharged(String v){
        rDateCharged.set(v);
    } 
    
}
