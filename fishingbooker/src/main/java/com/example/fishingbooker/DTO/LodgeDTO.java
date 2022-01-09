package com.example.fishingbooker.DTO;

public class LodgeDTO {

    private Integer owner;
    private String name;
    private String address;
    private String city;
    private String country;
    private Integer maxPersons;
    private String description;
    private String oneBed;
    private String twoBed;
    private String threeBed;
    private String fourBed;

    public LodgeDTO() {
    }

    public LodgeDTO(Integer owner, String name, String address, String city, String country, Integer maxPersons, String description, String oneBed, String twoBed, String threeBed, String fourBed) {
        this.owner = owner;
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.maxPersons = maxPersons;
        this.description = description;
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

    public void setMaxPersons(Integer maxPersons) {
        this.maxPersons = maxPersons;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
