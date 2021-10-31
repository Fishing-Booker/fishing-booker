package com.example.fishingbooker.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    private int id;
    private int entityId;
    private Date startDate;
    private Date endDate;
    private boolean isBooked;
    private int maxPersons;
    private double price;

    public Appointment() {
    }

    public Appointment(int id, int entityId, Date startDate, Date endDate, boolean isBooked, int maxPersons, double price) {
        this.id = id;
        this.entityId = entityId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isBooked = isBooked;
        this.maxPersons = maxPersons;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public int getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(int maxPersons) {
        this.maxPersons = maxPersons;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
