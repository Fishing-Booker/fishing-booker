package com.example.fishingbooker.DTO.reservationAction;

import java.util.Date;

public class ReservationActionDTO {

    private Integer actionId;
    private Date startDate;
    private Date endDate;
    private double price;
    private String additionalServices;
    private Integer maxPersons;
    private String bookedBy;

    public ReservationActionDTO() {
    }

    public ReservationActionDTO(Integer actionId, Date startDate, Date endDate, double price, String additionalServices,
                                Integer maxPersons, String bookedBy) {
        this.actionId = actionId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.additionalServices = additionalServices;
        this.maxPersons = maxPersons;
        this.bookedBy = bookedBy;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
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

    public String getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy;
    }
}
