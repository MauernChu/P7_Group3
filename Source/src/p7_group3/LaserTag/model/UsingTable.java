/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p7_group3.LaserTag.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author karatinka
 */
public class UsingTable {
    private final SimpleStringProperty urID;
    private final SimpleDoubleProperty udID;

    
    public UsingTable(String uID, double vID){
        this.urID = new SimpleStringProperty(uID);
        this.udID = new SimpleDoubleProperty(vID);
  }
    
   // Getters and setters 
    public String getUrID(){
        return urID.get();
    }
    
    public void setUrID(String uID){
        urID.set(uID);
    }
    
 
    
   // Getters and setters 
    public double getUdID(){
        return udID.get();
    }
    
    public void setUdID(double vID){
        udID.set(vID);
    }
    
    
}

