package com.example.fishingbooker.DTO.reservation;

import com.example.fishingbooker.Enum.ReservationType;

import java.util.Date;

public class ReservationForCalendarDTO {
    private Integer reservationId;
    private Date startDate;
    private Date endDate;
    private String clientName;
    private String clientSurname;
    private String entityName;
    private String regularService;
    private double price;
    private ReservationType reservationType;

    public ReservationForCalendarDTO() {
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getRegularService() {
        return regularService;
    }

    public void setRegularService(String regularService) {
        this.regularService = regularService;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ReservationType getReservationType() {
        return reservationType;
    }

    public void setReservationType(ReservationType reservationType) {
        this.reservationType = reservationType;
    }
}
