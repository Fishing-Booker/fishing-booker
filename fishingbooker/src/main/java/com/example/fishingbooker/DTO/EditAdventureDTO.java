package com.example.fishingbooker.DTO;

public class EditAdventureDTO {

    private Integer adventureId;
    private String name;
    private Integer locationId;
    private String description;
    private String biography;
    private Integer maxPersons;
    private String cancelConditions;
    private String fishingEquipment;
    private String address;
    private String city;
    private String country;

    public EditAdventureDTO() {
    }

    public EditAdventureDTO(Integer adventureId, String name, Integer locationId, String description, String biography, Integer maxPersons, String cancelConditions, String fishingEquipment,
                            String address, String city, String country) {
        this.adventureId = adventureId;
        this.name = name;
        this.locationId = locationId;
        this.description = description;
        this.biography = biography;
        this.maxPersons = maxPersons;
        this.cancelConditions = cancelConditions;
        this.fishingEquipment = fishingEquipment;
        this.address = address;
        this.city = city;
        this.country = country;
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

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
