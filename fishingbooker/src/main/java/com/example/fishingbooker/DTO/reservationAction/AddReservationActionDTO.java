package com.example.fishingbooker.DTO.reservationAction;

import java.util.Date;

public class AddReservationActionDTO {

    private Integer owner;
    private Integer entityId;
    private Date startDate;
    private Date endDate;
    private double price;
    private String additionalServices;
    private Integer maxPersons;

    public AddReservationActionDTO() {
    }

    public AddReservationActionDTO(Integer owner, Integer entityId, Date startDate, Date endDate, double price, String additionalServices, Integer maxPersons) {
        this.owner = owner;
        this.entityId = entityId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.additionalServices = additionalServices;
        this.maxPersons = maxPersons;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(String additionalServices) {
        this.additionalServices = additionalServices;
    }

    public Integer getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Integer maxPersons) {
        this.maxPersons = maxPersons;
    }
}
