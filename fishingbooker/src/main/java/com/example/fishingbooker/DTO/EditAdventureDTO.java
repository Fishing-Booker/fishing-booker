package com.example.fishingbooker.DTO;

public class EditAdventureDTO {

    private Integer adventureId;
    private String name;
    private String description;
    private String biography;
    private Integer maxPersons;
    private String cancelConditions;
    private String fishingEquipment;

    public EditAdventureDTO() {
    }

    public EditAdventureDTO(Integer adventureId, String name, String description, String biography, Integer maxPersons, String cancelConditions, String fishingEquipment) {
        this.adventureId = adventureId;
        this.name = name;
        this.description = description;
        this.biography = biography;
        this.maxPersons = maxPersons;
        this.cancelConditions = cancelConditions;
        this.fishingEquipment = fishingEquipment;
    }

    public Integer getAdventureId() {
        return adventureId;
    }

    public void setAdventureId(Integer adventureId) {
        this.adventureId = adventureId;
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

    public String getCancelConditions() {
        return cancelConditions;
    }

    public void setCancelConditions(String cancelConditions) {
        this.cancelConditions = cancelConditions;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(String fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }
}
