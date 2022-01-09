package com.example.fishingbooker.DTO.reservationPeriod;

import java.util.Date;

public class AddReservationPeriodDTO {

    private Integer owner;
    private Date startDate;
    private Date endDate;
    private Integer entityId;

    public AddReservationPeriodDTO() {
    }

    public AddReservationPeriodDTO(Integer owner, Date startDate, Date endDate, Integer entityId) {
        this.owner = owner;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }
}
