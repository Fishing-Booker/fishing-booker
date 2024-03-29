package com.example.fishingbooker.DTO;

public class AdventureDTO {
    private Integer owner;
    private String name;
    private String description;
    private String biography;
    private Integer maxPersons;
    private double cancelConditions;
    private String fishingEquipment;

    public AdventureDTO() {
    }

    public AdventureDTO(Integer owner, String name, String description, String biography, Integer maxPersons,
                        double cancelConditions, String fishingEquipment) {
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.biography = biography;
        this.maxPersons = maxPersons;
        this.cancelConditions = cancelConditions;
        this.fishingEquipment = fishingEquipment;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Integer getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Integer maxPersons) {
        this.maxPersons = maxPersons;
    }

    public double getCancelConditions() {
        return cancelConditions;
    }

    public void setCancelConditions(double cancelConditions) {
        this.cancelConditions = cancelConditions;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(String fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }
}
