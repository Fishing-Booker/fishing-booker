package com.example.fishingbooker.DTO;

public class EntityDTO {
    private Integer serialNumber;
    private Integer entityId;
    private Integer ownerId;
    private String name;
    private String location;
    private String description;
    private double cancelConditions;
    private double averageGrade;
    private Integer maxPersons;
    private String profileImage;

    public EntityDTO() {
    }

    public EntityDTO(Integer serialNumber,Integer entityId, Integer ownerId, String name, String location, String description,
                     double cancelConditions, double averageGrade, Integer maxPersons, String profileImage) {
        this.serialNumber = serialNumber;
        this.entityId = entityId;
        this.ownerId = ownerId;
        this.name = name;
        this.location = location;
        this.description = description;
        this.cancelConditions = cancelConditions;
        this.averageGrade = averageGrade;
        this.maxPersons = maxPersons;
        this.profileImage = profileImage;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCancelConditions() {
        return cancelConditions;
    }

    public void setCancelConditions(double cancelConditions) {
        this.cancelConditions = cancelConditions;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public Integer getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Integer maxPersons) {
        this.maxPersons = maxPersons;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
