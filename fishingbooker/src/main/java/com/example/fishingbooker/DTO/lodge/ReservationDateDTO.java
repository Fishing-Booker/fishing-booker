package com.example.fishingbooker.DTO.lodge;

import java.util.Date;

public class ReservationDateDTO {
    private Date date;
    private Integer clientId;

    public ReservationDateDTO() {
    }

    public ReservationDateDTO(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
}
