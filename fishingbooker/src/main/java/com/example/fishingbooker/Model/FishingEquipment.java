package com.example.fishingbooker.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fishingEquipment")
public class FishingEquipment {

    @Id
    private int id;
    private int entityId;
    private String name;
    private boolean isDeleted;

    public FishingEquipment() {
    }

    public FishingEquipment(int id, int entityId, String name) {
        this.id = id;
        this.entityId = entityId;
        this.name = name;
        this.isDeleted = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
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
