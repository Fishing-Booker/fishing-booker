package com.example.fishingbooker.DTO.reservationPeriod;

import java.util.Date;

public class GetReservationPeriodDTO {

    private Integer id;
    private Date startDate;
    private Date endDate;
    private Integer entityId;

    public GetReservationPeriodDTO() {
    }

    public GetReservationPeriodDTO(Integer id, Date startDate, Date endDate, Integer entityId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.entityId = entityId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
