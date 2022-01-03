package com.example.fishingbooker.DTO;

public class UpdateShipDTO {

    private String name;
    private Integer locationId;
    private String address;
    private String city;
    private String country;
    private String description;
    private String shipType;
    private double length;
    private Integer engineNumber;
    private double enginePower;
    private double maxSpeed;
    private Integer capacity;

    public UpdateShipDTO() {
    }

    public UpdateShipDTO(String name, Integer locationId, String address, String city, String country,
                         String description, String shipType, double length, Integer engineNumber, double enginePower,
                         double maxSpeed, Integer capacity) {
        this.name = name;
        this.locationId = locationId;
        this.address = address;
        this.city = city;
        this.country = country;
        this.description = description;
        this.shipType = shipType;
        this.length = length;
        this.engineNumber = engineNumber;
        this.enginePower = enginePower;
        this.maxSpeed = maxSpeed;
        this.capacity = capacity;
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

    public Integer getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(Integer engineNumber) {
        this.engineNumber = engineNumber;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(double enginePower) {
        this.enginePower = enginePower;
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
}
