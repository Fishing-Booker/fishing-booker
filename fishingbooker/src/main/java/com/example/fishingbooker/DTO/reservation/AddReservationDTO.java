package com.example.fishingbooker.DTO.reservation;

import java.util.Date;

public class AddReservationDTO {

    private Integer owner;
    private Date startDate;
    private Date endDate;
    private Integer clientId;
    private Integer entityId;

    public AddReservationDTO() {
    }

    public AddReservationDTO(Integer owner, Date startDate, Date endDate, Integer clientId, Integer entityId) {
        this.owner = owner;
        this.startDate = startDate;
        this.endDate = endDate;
        this.clientId = clientId;
        this.entityId = entityId;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
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
