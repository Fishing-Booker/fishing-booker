package com.example.fishingbooker.DTO.reservation;

import java.util.Date;

public class ReservationDTO {

    private Integer reservationId;
    private Date startDate;
    private Date endDate;
    private String clientUsername;
    private Integer entityId;
    private String entityName;

    public ReservationDTO() {
    }

    public ReservationDTO(Integer reservationId, Date startDate, Date endDate, String clientUsername, Integer entityId, String entityName) {
        this.reservationId = reservationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.clientUsername = clientUsername;
        this.entityId = entityId;
        this.entityName = entityName;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
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

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
