package com.example.fishingbooker.Model;

import com.example.fishingbooker.Enum.ReservationType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservation_action")
@Inheritance(strategy = InheritanceType.JOINED)
public class ReservationAction extends Reservation{

    @Column(name = "price")
    private double price;

    @Column(name = "max_persons")
    private Integer maxPersons;

    @Column(name = "additional_services")
    private String additionalServices;

    @Column(name = "is_booked")
    private boolean isBooked;

    public ReservationAction() {
    }

    public ReservationAction(double price, Integer maxPersons, String additionalServices) {
        this.price = price;
        this.maxPersons = maxPersons;
        this.additionalServices = additionalServices;
    }

    public ReservationAction(Integer id, Date startDate, Date endDate, ReservationEntity reservationEntity, User client, ReservationType reservationType, double price, Integer maxPersons, String additionalServices, boolean isBooked) {
        super(id, startDate, endDate, reservationEntity, client, reservationType);
        this.price = price;
        this.maxPersons = maxPersons;
        this.additionalServices = additionalServices;
        this.isBooked = isBooked;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Integer maxPersons) {
        this.maxPersons = maxPersons;
    }

    public String getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(String additionalServices) {
        this.additionalServices = additionalServices;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
