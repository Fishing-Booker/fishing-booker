package com.example.fishingbooker.DTO.reservation;

import java.util.Date;

public class OwnerReservationDTO {
    private Integer clientId;
    private Integer entityId;
    private Date startDate;
    private Date endDate;
    private Integer numberOfGuests;
    private double price;
    private String additionalServices;
    private String regularService;

    public OwnerReservationDTO() {
    }

    public OwnerReservationDTO(Integer clientId, Integer entityId, Date startDate, Date endDate, Integer numberOfGuests, double price, String additionalServices, String regularService) {
        this.clientId = clientId;
        this.entityId = entityId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.additionalServices = additionalServices;
        this.regularService = regularService;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
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

    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
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

    public String getRegularService() {
        return regularService;
    }

    public void setRegularService(String regularService) {
        this.regularService = regularService;
    }
}
