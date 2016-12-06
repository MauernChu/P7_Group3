/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p7_group3.LaserTag.model;

/**
 *
 * @author Mette
 */
public class Game {
    public Equipment equipment;
    
    public Game(Equipment equipment){
        this.equipment = equipment;
    }
    
   public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
}
    

  
