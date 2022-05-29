package com.example.fishingbooker.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "ship")
public class Ship extends ReservationEntity {

    @Column(name = "ship_type")
    private String shipType;

    @Column(name = "length")
    private double length;

    @Column(name = "engine_num")
    private Integer engineNumber;

    @Column(name = "engine_power")
    private double enginePower;

    @Column(name = "max_speed")
    private double maxSpeed;

    @Column(name = "nav_equipment")
    private String navigationEquipment;

    public Ship() {
    }

    public Ship(Integer id, User owner, String name, Location location, String description, String rules, double cancelConditions,
                double averageGrade, Integer maxPersons, List<Image> images, String shipType, double length, Integer engineNumber,
                double enginePower, double maxSpeed, String navigationEquipment) {
        super(id, owner, name, location, description, rules, cancelConditions, averageGrade, maxPersons);
        this.shipType = shipType;
        this.length = length;
        this.engineNumber = engineNumber;
        this.enginePower = enginePower;
        this.maxSpeed = maxSpeed;
        this.navigationEquipment = navigationEquipment;
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

    public String getNavigationEquipment() {
        return navigationEquipment;
    }

    public void setNavigationEquipment(String navigationEquipment) {
        this.navigationEquipment = navigationEquipment;
    }
}
