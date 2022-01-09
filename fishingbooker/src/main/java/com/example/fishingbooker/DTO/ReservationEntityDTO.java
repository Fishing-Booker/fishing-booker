package com.example.fishingbooker.DTO;

public class ReservationEntityDTO {

    private Integer id;
    private String name;
    private Integer locationId;
    private String description;
    private String rules;
    private String cancelConditions;
    private double averageGrade;
    private Integer maxPersons;

    public ReservationEntityDTO() {
    }

    public ReservationEntityDTO(Integer id, String name, Integer locationId, String description, String rules, String cancelConditions, double averageGrade, Integer maxPersons) {
        this.id = id;
        this.name = name;
        this.locationId = locationId;
        this.description = description;
        this.rules = rules;
        this.cancelConditions = cancelConditions;
        this.averageGrade = averageGrade;
        this.maxPersons = maxPersons;
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
}
