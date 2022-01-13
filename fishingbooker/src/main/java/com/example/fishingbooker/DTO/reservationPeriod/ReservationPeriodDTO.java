package com.example.fishingbooker.DTO.reservationPeriod;

import java.util.Date;

public class ReservationPeriodDTO {

    private Date startDate;
    private Date endDate;
    private Integer entityId;
    private Integer ownerId;

    public ReservationPeriodDTO() {
    }

    public ReservationPeriodDTO(Date startDate, Date endDate, Integer entityId) {
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
}
