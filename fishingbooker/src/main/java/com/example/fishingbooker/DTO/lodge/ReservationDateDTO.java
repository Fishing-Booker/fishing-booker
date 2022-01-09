package com.example.fishingbooker.DTO.lodge;

import java.util.Date;

public class ReservationDateDTO {
    private Date date;

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
}
