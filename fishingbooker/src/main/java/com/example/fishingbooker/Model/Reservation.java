package com.example.fishingbooker.Model;

import com.example.fishingbooker.Enum.ReservationType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation extends Appointment {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="client_id", referencedColumnName = "user_id")
    private User client;

    @Column(name = "reservation_type")
    @Enumerated(EnumType.STRING)
    private ReservationType reservationType;

    public Reservation() {
    }

    public Reservation(Integer id, ReservationEntity reservationEntity, Date startDate, Date endDate, boolean isBooked, Integer maxPersons, double price,
                       User client, ReservationType reservationType) {
        super(id, reservationEntity, startDate, endDate, isBooked, maxPersons, price);
        this.client = client;
        this.reservationType = reservationType;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public ReservationType getReservationType() {
        return reservationType;
    }

    public void setReservationType(ReservationType reservationType) {
        this.reservationType = reservationType;
    }
}
