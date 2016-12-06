/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p7_group3.LaserTag.model;

import java.util.Date;

/**
 *
 * @author Mette
 */
public class Maintenance {
    
    private String damageDescription;
    private Date dateBroken;
    private Date dateRepaired;

    public Maintenance(String damageDescription, Date dateBroken, Date dateRepaired) {
        this.damageDescription = damageDescription;
        this.dateBroken = dateBroken;
        this.dateRepaired = dateRepaired;
    }

    public String getDamageDescription() {
        return damageDescription;
    }

    public void setDamageDescription(String damageDescription) {
        this.damageDescription = damageDescription;
    }

    public Date getDateBroken() {
        return dateBroken;
    }

    public void setDateBroken(Date dateBroken) {
        this.dateBroken = dateBroken;
    }

    public Date getDateRepaired() {
        return dateRepaired;
    }

    public void setDateRepaired(Date dateRepaired) {
        this.dateRepaired = dateRepaired;
    }
}
