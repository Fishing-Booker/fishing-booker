package com.example.fishingbooker.DTO.lodge;

public class AddLodgeDTO {

    private Integer owner;
    private String name;
    private Integer maxPersons;
    private String description;
    private double cancelConditions;
    private String oneBed;
    private String twoBed;
    private String threeBed;
    private String fourBed;

    public AddLodgeDTO() {
    }

    public AddLodgeDTO(Integer owner, String name, Integer maxPersons, String description, double cancelConditions,
                       String oneBed, String twoBed, String threeBed, String fourBed) {
        this.owner = owner;
        this.name = name;
        this.maxPersons = maxPersons;
        this.description = description;
        this.cancelConditions = cancelConditions;
        this.oneBed = oneBed;
        this.twoBed = twoBed;
        this.threeBed = threeBed;
        this.fourBed = fourBed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Integer maxPersons) {
        this.maxPersons = maxPersons;
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

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public String getOneBed() {
        return oneBed;
    }

    public void setOneBed(String oneBed) {
        this.oneBed = oneBed;
    }

    public String getTwoBed() {
        return twoBed;
    }

    public void setTwoBed(String twoBed) {
        this.twoBed = twoBed;
    }

    public String getThreeBed() {
        return threeBed;
    }

    public void setThreeBed(String threeBed) {
        this.threeBed = threeBed;
    }

    public String getFourBed() {
        return fourBed;
    }

    public void setFourBed(String fourBed) {
        this.fourBed = fourBed;
    }
}
