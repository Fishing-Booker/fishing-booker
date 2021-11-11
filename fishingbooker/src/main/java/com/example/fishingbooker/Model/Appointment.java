package com.example.fishingbooker.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appointment")
@Inheritance(strategy = InheritanceType.JOINED)
public class Appointment {

    @Id
    @Column(name = "appointment_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "entity_id", referencedColumnName = "entity_id")
    private ReservationEntity reservationEntity;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "is_booked")
    private boolean isBooked;

    @Column(name = "max_persons")
    private Integer maxPersons;

    @Column(name = "price")
    private double price;

    public Appointment() {
    }

    public Appointment(Integer id, ReservationEntity reservationEntity, Date startDate, Date endDate, boolean isBooked,
                       Integer maxPersons, double price) {
        this.id = id;
        this.reservationEntity = reservationEntity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isBooked = isBooked;
        this.maxPersons = maxPersons;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReservationEntity getReservationEntity() {
        return reservationEntity;
    }

    public void setReservationEntity(ReservationEntity reservationEntity) {
        this.reservationEntity = reservationEntity;
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

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public Integer getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Integer maxPersons) {
        this.maxPersons = maxPersons;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
