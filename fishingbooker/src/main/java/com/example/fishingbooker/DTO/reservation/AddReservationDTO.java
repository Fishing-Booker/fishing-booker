package com.example.fishingbooker.DTO.reservation;

import java.util.Date;

public class AddReservationDTO {

    private Integer owner;
    private Date startDate;
    private Date endDate;
    private String clientUsername;
    private Integer entityId;

    public AddReservationDTO() {
    }

    public AddReservationDTO(Integer owner, Date startDate, Date endDate, String clientUsername, Integer entityId) {
        this.owner = owner;
        this.startDate = startDate;
        this.endDate = endDate;
        this.clientUsername = clientUsername;
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

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }
}
