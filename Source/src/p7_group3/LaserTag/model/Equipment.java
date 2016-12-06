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
public class Equipment {
    public int id;
    public String name;
    public ChargingStatus chargingStatus; //Nested class, lies under equipment.
    public Maintenance maintenance;

    public Equipment(int id, String name, ChargingStatus chargingStatus, Maintenance maintenance) {
        this.id = id;
        this.name = name;
        this.chargingStatus = chargingStatus;
        this.maintenance = maintenance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChargingStatus getChargingStatus() {
        return chargingStatus;
    }

    public void setChargingStatus(ChargingStatus chargingStatus) {
        this.chargingStatus = chargingStatus;
    }

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }
}
