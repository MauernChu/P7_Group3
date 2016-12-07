/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p7_group3.LaserTag.model;

import java.util.Date;
import java.sql.Time;

/**
 *
 * @author Mette
 */
public class ChargingStatus {
    
    private Date dateCharged;
    public double time; 

    public ChargingStatus(Date dateCharged) {
        time = dateCharged.getTime();
        this.time = time;
        this.dateCharged = dateCharged;
        
    }
    
    public double getTime() {
        return time;
    }

    public Date getDateCharged() {
        return dateCharged;
    }

    public void setDateCharged(Date dateCharged) {
        this.dateCharged = dateCharged;
    }
}
