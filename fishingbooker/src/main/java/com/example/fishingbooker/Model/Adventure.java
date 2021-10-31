package com.example.fishingbooker.Model;

import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "adventure")
public class Adventure extends Entity {
    private String biography;
    private int maxPersons;

    public Adventure() {
    }

    public Adventure(int id, String ownerUsername, String name, int locationId, String description, String rules, String cancelConditions, double averageGrade, String biography, int maxPersons) {
        super(id, ownerUsername, name, locationId, description, rules, cancelConditions, averageGrade);
        this.biography = biography;
        this.maxPersons = maxPersons;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public int getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(int maxPersons) {
        this.maxPersons = maxPersons;
    }
}
