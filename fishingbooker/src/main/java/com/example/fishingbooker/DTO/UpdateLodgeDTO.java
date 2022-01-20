package com.example.fishingbooker.DTO;

public class UpdateLodgeDTO {
    private String name;
    private Integer locationId;
    private String address;
    private String city;
    private String country;
    private Integer maxPersons;
    private String description;
    private double cancelConditions;
    private Integer oneBed;
    private Integer twoBed;
    private Integer threeBed;
    private Integer fourBed;

    public UpdateLodgeDTO() {
    }

    public UpdateLodgeDTO(String name, Integer locationId, String address, String city, String country, Integer maxPersons,
                          String description, double cancelConditions, Integer oneBed, Integer twoBed, Integer threeBed, Integer fourBed) {
        this.name = name;
        this.locationId = locationId;
        this.address = address;
        this.city = city;
        this.country = country;
        this.maxPersons = maxPersons;
        this.description = description;
        this.cancelConditions = cancelConditions;
        this.oneBed = oneBed;
        this.twoBed = twoBed;
        this.threeBed = threeBed;
        this.fourBed = fourBed;
    }

    public Integer getOneBed() {
        return oneBed;
    }

    public void setOneBed(Integer oneBed) {
        this.oneBed = oneBed;
    }

    public Integer getTwoBed() {
        return twoBed;
    }

    public void setTwoBed(Integer twoBed) {
        this.twoBed = twoBed;
    }

    public Integer getThreeBed() {
        return threeBed;
    }

    public void setThreeBed(Integer threeBed) {
        this.threeBed = threeBed;
    }

    public Integer getFourBed() {
        return fourBed;
    }

    public void setFourBed(Integer fourBed) {
        this.fourBed = fourBed;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Integer maxPrersons) {
        this.maxPersons = maxPrersons;
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
}
