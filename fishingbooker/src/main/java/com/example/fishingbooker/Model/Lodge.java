package com.example.fishingbooker.Model;

import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "lodge")
public class Lodge extends Entity {
    private int roomNumber;
    private int bedNumber;

    public Lodge() {
    }

    public Lodge(int id, String ownerUsername, String name, int locationId, String description, String rules, String cancelConditions, double averageGrade, int roomNumber, int bedNumber) {
        super(id, ownerUsername, name, locationId, description, rules, cancelConditions, averageGrade);
        this.roomNumber = roomNumber;
        this.bedNumber = bedNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }
}
