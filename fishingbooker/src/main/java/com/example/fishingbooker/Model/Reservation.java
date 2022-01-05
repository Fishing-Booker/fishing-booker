package com.example.fishingbooker.Model;

import com.example.fishingbooker.Enum.ReservationType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(optional = false)
    @JoinColumn(name = "period_id", referencedColumnName = "id")
    private ReservationPeriod reservationPeriod;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="client_id", referencedColumnName = "user_id")
    private User client;

    @Column(name = "reservation_type")
    @Enumerated(EnumType.STRING)
    private ReservationType reservationType;

    public Reservation() {
    }

    public Reservation(Integer id, ReservationPeriod reservationPeriod, User client, ReservationType reservationType) {
        this.id = id;
        this.reservationPeriod = reservationPeriod;
        this.client = client;
        this.reservationType = reservationType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReservationPeriod getReservationPeriod() {
        return reservationPeriod;
    }

    public void setReservationPeriod(ReservationPeriod reservationPeriod) {
        this.reservationPeriod = reservationPeriod;
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
