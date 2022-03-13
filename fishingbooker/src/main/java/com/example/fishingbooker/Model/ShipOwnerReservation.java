package com.example.fishingbooker.Model;

import javax.persistence.*;

@Entity
@Table(name = "ship_owner_reservation")
@Inheritance(strategy = InheritanceType.JOINED)
public class ShipOwnerReservation {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Reservation.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private Reservation reservation;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.ALL})
    //@JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    private User owner;

    public ShipOwnerReservation() {
    }

    public ShipOwnerReservation(Integer id, Reservation reservation, User owner) {
        this.id = id;
        this.reservation = reservation;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
