package com.example.fishingbooker.DTO.reservationPeriodOwner;

import java.util.Date;

public class ReservationPeriodOwnerDTO {
    private Integer owner;
    private Date startDate;
    private Date endDate;

    public ReservationPeriodOwnerDTO() {
    }

    public ReservationPeriodOwnerDTO(Integer owner, Date startDate, Date endDate) {
        this.owner = owner;
        this.startDate = startDate;
        this.endDate = endDate;
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
}
