package com.example.fishingbooker.DTO.ship;

import com.example.fishingbooker.DTO.lodge.ImageDTO;
import com.example.fishingbooker.DTO.lodge.LocationDTO;
import com.example.fishingbooker.DTO.lodge.OwnerDTO;

public class ShipInfoDTO {
    private Integer id;
    private String name;
    private String description;
    private double averageGrade;
    private String rules;
    private double cancelConditions;
    private String shipType;
    private double length;
    private double maxSpeed;
    private Integer capacity;
    private LocationDTO location;
    private ImageDTO images;
    private OwnerDTO owner;
    private Integer maxPersons;

    public ShipInfoDTO() {
    }

    public ShipInfoDTO(Integer id, String name, String description, double averageGrade, String rules, double cancelConditions, String shipType, double length, double maxSpeed,
                       Integer capacity, LocationDTO location, ImageDTO images, OwnerDTO owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.averageGrade = averageGrade;
        this.rules = rules;
        this.cancelConditions = cancelConditions;
        this.shipType = shipType;
        this.length = length;
        this.maxSpeed = maxSpeed;
        this.capacity = capacity;
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

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
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

    public Integer getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Integer maxPersons) {
        this.maxPersons = maxPersons;
    }
}
