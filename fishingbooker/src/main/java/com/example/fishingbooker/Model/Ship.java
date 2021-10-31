package com.example.fishingbooker.Model;

import javax.persistence.ForeignKey;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "ship")
public class Ship extends Entity {

    private String shipType;
    private double length;
    private int engineNumber;
    private double enginePower;
    private double maxSpeed;
    private int capacity;

    public Ship() {
    }

    public Ship(int id, String ownerUsername, String name, int locationId, String description, String rules, String cancelConditions,
                double averageGrade, String shipType, double length, int engineNumber, double enginePower, double maxSpeed, int capacity) {
        super(id, ownerUsername, name, locationId, description, rules, cancelConditions, averageGrade);
        this.shipType = shipType;
        this.length = length;
        this.engineNumber = engineNumber;
        this.enginePower = enginePower;
        this.maxSpeed = maxSpeed;
        this.capacity = capacity;
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

    public int getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(int engineNumber) {
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
