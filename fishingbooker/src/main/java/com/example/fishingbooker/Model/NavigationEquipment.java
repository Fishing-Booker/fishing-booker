package com.example.fishingbooker.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "navigationEquipemnt")
public class NavigationEquipment {

    @Id
    private int id;
    private int shipId;
    private String name;
    private boolean isDeleted;

    public NavigationEquipment() {
    }

    public NavigationEquipment(int id, int shipId, String name) {
        this.id = id;
        this.shipId = shipId;
        this.name = name;
        this.isDeleted = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
