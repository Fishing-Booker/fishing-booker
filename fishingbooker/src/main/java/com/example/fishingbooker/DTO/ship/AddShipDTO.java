package com.example.fishingbooker.DTO.ship;

public class AddShipDTO {

    private Integer owner;
    private String name;
    private String description;
    private String shipType;
    private double length;
    private Integer engineNumber;
    private double enginePower;
    private double maxSpeed;
    private Integer capacity;

    public AddShipDTO() {
    }

    public AddShipDTO(Integer owner, String name, String description, String shipType, double length,
                      Integer engineNumber, double enginePower, double maxSpeed, Integer capacity) {
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.shipType = shipType;
        this.length = length;
        this.engineNumber = engineNumber;
        this.enginePower = enginePower;
        this.maxSpeed = maxSpeed;
        this.capacity = capacity;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
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
