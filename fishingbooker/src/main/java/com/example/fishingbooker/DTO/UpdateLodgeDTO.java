package com.example.fishingbooker.DTO;

public class UpdateLodgeDTO {
    private String name;
    private Integer locationId;
    private String address;
    private String city;
    private String country;
    private String description;

    public UpdateLodgeDTO() {
    }

    public UpdateLodgeDTO(String name, Integer locationId, String address, String city, String country, String description) {
        this.name = name;
        this.locationId = locationId;
        this.address = address;
        this.city = city;
        this.country = country;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
