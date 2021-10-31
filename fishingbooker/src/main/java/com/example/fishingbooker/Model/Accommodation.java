package com.example.fishingbooker.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accommodation")
public class Accommodation {

    @Id
    private int id;
    private int entityId;
    private String name;

    public Accommodation() {
    }

    public Accommodation(int id, int entityId, String name) {
        this.id = id;
        this.entityId = entityId;
        this.name = name;
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
}
