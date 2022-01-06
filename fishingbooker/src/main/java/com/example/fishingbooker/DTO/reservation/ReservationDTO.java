package com.example.fishingbooker.DTO.reservation;

import java.util.Date;

public class ReservationDTO {

    private Date startDate;
    private Date endDate;
    private Integer clientId;
    private Integer entityId;

    public ReservationDTO() {
    }

    public ReservationDTO(Date startDate, Date endDate, Integer clientId, Integer entityId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.clientId = clientId;
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
}
