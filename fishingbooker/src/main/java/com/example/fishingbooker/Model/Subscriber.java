package com.example.fishingbooker.Model;

import javax.persistence.*;

@Entity
@Table(name = "subscriber")
public class Subscriber {

    @Id
    @Column(name = "subscriber_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name="client_id", referencedColumnName = "user_id")
    private User client;

    @ManyToOne(targetEntity = ReservationEntity.class, optional = false, cascade = {CascadeType.MERGE})
    //@JoinColumn(name = "entity_id", referencedColumnName = "entity_id")
    private ReservationEntity reservationEntity;

    public Subscriber() {
    }

    public Subscriber(Integer id, User client, ReservationEntity reservationEntity) {
        this.id = id;
        this.client = client;
        this.reservationEntity = reservationEntity;
    }

    public Subscriber(User client, ReservationEntity reservationEntity) {
        this.client = client;
        this.reservationEntity = reservationEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public ReservationEntity getReservationEntity() {
        return reservationEntity;
    }

    public void setReservationEntity(ReservationEntity reservationEntity) {
        this.reservationEntity = reservationEntity;
    }
}
