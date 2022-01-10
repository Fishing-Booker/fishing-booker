package com.example.fishingbooker.DTO.reservation;

import java.util.Date;

public class AddReservationDTO {

    private Integer owner;
    private String entityName;
    private Date startDate;
    private Date endDate;
    private String clientUsername;

    public AddReservationDTO() {
    }

    public AddReservationDTO(Integer owner, String entityName, Date startDate, Date endDate, String clientUsername) {
        this.owner = owner;
        this.entityName = entityName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.clientUsername = clientUsername;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
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
}
