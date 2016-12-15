package p7_group3.LaserTag.model;

import java.util.ArrayList;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Equipment {
    
    public static ArrayList<String> equipmentList = new ArrayList();
    
    public StringProperty id;
    public String name;
    public ChargingStatus chargingStatus; //Nested class, lies under equipment.
    public Maintenance maintenance;
    public SimpleBooleanProperty checkbox;
    public SimpleBooleanProperty maintenanceCheckbox;

    public Equipment(String id2, String name, ChargingStatus chargingStatus, Maintenance maintenance, Boolean checkbox, Boolean maintenanceCheckbox) {
        this.id = new SimpleStringProperty(id2);
        this.name = name;
        this.chargingStatus = chargingStatus;
        this.maintenance = maintenance;
        this.checkbox = new SimpleBooleanProperty(checkbox);
        this.maintenanceCheckbox = new SimpleBooleanProperty(maintenanceCheckbox);
    
    
                this.checkbox.addListener(new ChangeListener<Boolean>() {
                
                public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                    if(t == false){
                    equipmentList.add(idProperty().get());
                    } else{
                        equipmentList.remove(idProperty().get());
                    }
               
                   
                }
                
            });
    
    
    }
    
     public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }
    
    public Boolean getCheckbox() {
        return checkbox.get();
    }

    public void setCheckbox(Boolean c) {
        this.checkbox.setValue(c);
    }
    
       public Boolean getMaintenanceCheckbox() {
        return checkbox.get();
    }

    public void setMaintenanceCheckbox(Boolean m) {
        this.checkbox.setValue(m);
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
