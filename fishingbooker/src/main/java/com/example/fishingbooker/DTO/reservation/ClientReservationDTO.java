package com.example.fishingbooker.DTO.reservation;

import java.util.Date;

public class ClientReservationDTO {
    private Integer clientId;
    private Integer entityId;
    private Date startDate;
    private Date endDate;
    private Integer numberOfGuests;

    public ClientReservationDTO() {
    }

    public ClientReservationDTO(Integer clientId, Integer entityId, Date startDate, Date endDate, Integer numberOfGuests) {
        this.clientId = clientId;
        this.entityId = entityId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfGuests = numberOfGuests;
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
}