package com.example.fishingbooker.Model;

import javax.persistence.Id;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "entity")
public class Entity {

    @Id
    private int id;
    private String ownerUsername;
    private String name;
    private int locationId;
    private String description;
    private String rules;
    private String cancelConditions;
    private boolean isDeleted;
    private double averageGrade;

    public Entity() {
    }

    public Entity(int id, String ownerUsername, String name, int locationId, String description, String rules, String cancelConditions, double averageGrade) {
        this.id = id;
        this.ownerUsername = ownerUsername;
        this.name = name;
        this.locationId = locationId;
        this.description = description;
        this.rules = rules;
        this.cancelConditions = cancelConditions;
        this.isDeleted = false;
        this.averageGrade = averageGrade;
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

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getCancelConditions() {
        return cancelConditions;
    }

    public void setCancelConditions(String cancelConditions) {
        this.cancelConditions = cancelConditions;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }
}
