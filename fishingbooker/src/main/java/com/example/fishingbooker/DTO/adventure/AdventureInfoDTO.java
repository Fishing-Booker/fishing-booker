package com.example.fishingbooker.DTO.adventure;

import com.example.fishingbooker.DTO.lodge.ImageDTO;
import com.example.fishingbooker.DTO.lodge.LocationDTO;
import com.example.fishingbooker.DTO.lodge.OwnerDTO;

public class AdventureInfoDTO {
    private Integer id;
    private String name;
    private String description;
    private double averageGrade;
    private String rules;
    private double cancelConditions;
    private String biography;
    private Integer maxPersons;
    private LocationDTO location;
    private ImageDTO images;
    private OwnerDTO owner;

    public AdventureInfoDTO() {
    }

    public AdventureInfoDTO(Integer id, String name, String description, double averageGrade, String rules, double cancelConditions, String biography,
                            Integer maxPersons, LocationDTO location, ImageDTO images, OwnerDTO owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.averageGrade = averageGrade;
        this.rules = rules;
        this.cancelConditions = cancelConditions;
        this.biography = biography;
        this.maxPersons = maxPersons;
        this.location = location;
        this.images = images;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public double getCancelConditions() {
        return cancelConditions;
    }

    public void setCancelConditions(double cancelConditions) {
        this.cancelConditions = cancelConditions;
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

    public OwnerDTO getOwner() {
        return owner;
    }

    public void setOwner(OwnerDTO owner) {
        this.owner = owner;
    }
}
