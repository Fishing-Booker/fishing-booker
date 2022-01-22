package com.example.fishingbooker.DTO.reservation;

import java.util.Date;

public class ReservationDTO {

    private Integer reservationId;
    private Date startDate;
    private Date endDate;
    private String clientUsername;
    private Integer entityId;
    private String entityName;
    private String additionalService;
    private String regularService;
    private double price;
    private String reservationType;
    private Integer maxPersons;

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

    public ReservationDTO(Integer reservationId, Date startDate, Date endDate, String clientUsername, Integer entityId,
                          String entityName, String additionalService, String regularService, double price,
                          String reservationType, Integer maxPersons) {
        this.reservationId = reservationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.clientUsername = clientUsername;
        this.entityId = entityId;
        this.entityName = entityName;
        this.additionalService = additionalService;
        this.regularService = regularService;
        this.price = price;
        this.reservationType = reservationType;
        this.maxPersons = maxPersons;
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

    public String getAdditionalService() {
        return additionalService;
    }

    public void setAdditionalService(String additionalService) {
        this.additionalService = additionalService;
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

    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }

    public Integer getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Integer maxPersons) {
        this.maxPersons = maxPersons;
    }
}
