package p7_group3.LaserTag.model;

import java.util.Date;

public class Maintenance {
    
    private String damageDescription;
    private Date dateBroken;
    private String nameDiscoverDamage;
    private String damageDefinition;
    

    public Maintenance(String damageDescription, Date dateBroken, String nameDiscoverDamage, String damageDefinition) {
        this.damageDescription = damageDescription;
        this.dateBroken = dateBroken;
        this.nameDiscoverDamage = nameDiscoverDamage;
        this.damageDefinition = damageDefinition;
        
       
    }

    public String getNameDiscoverDamage() {
        return nameDiscoverDamage;
    }

    public void setNameDiscoverDamage(String nameDiscoverDamage) {
        this.nameDiscoverDamage = nameDiscoverDamage;
    }

    public String getDamageDefinition() {
        return damageDefinition;
    }

    public void setDamageDefinition(String damageDefinition) {
        this.damageDefinition = damageDefinition;
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
}
