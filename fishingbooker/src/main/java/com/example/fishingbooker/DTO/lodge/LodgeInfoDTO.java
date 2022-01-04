package com.example.fishingbooker.DTO.lodge;

public class LodgeInfoDTO {
    String name;
    String description;
    double averageGrade;
    String rules;
    String cancelConditions;
    LocationDTO location;
    ImageDTO images;
    BedroomDTO bedroom;
    OwnerDTO owner;

    public LodgeInfoDTO() {
    }

    public LodgeInfoDTO(String name, String description, double averageGrade, String rules, String cancelConditions, LocationDTO location, ImageDTO images, BedroomDTO bedroom, OwnerDTO owner) {
        this.name = name;
        this.description = description;
        this.averageGrade = averageGrade;
        this.rules = rules;
        this.cancelConditions = cancelConditions;
        this.location = location;
        this.images = images;
        this.bedroom = bedroom;
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

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
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

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public ImageDTO getImages() {
        return images;
    }

    public void setImages(ImageDTO images) {
        this.images = images;
    }

    public BedroomDTO getBedroom() {
        return bedroom;
    }

    public void setBedroom(BedroomDTO bedroom) {
        this.bedroom = bedroom;
    }

    public OwnerDTO getOwner() {
        return owner;
    }

    public void setOwner(OwnerDTO owner) {
        this.owner = owner;
    }
}
