package com.example.fishingbooker.Model;

import javax.persistence.*;

@Entity
@Table(name = "complaint")
public class Complaint {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "entity_id", referencedColumnName = "entity_id")
    private ReservationEntity reservationEntity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "user_id")
    private User client;

    @Column(name = "is_responded")
    private Boolean isResponded;

    public Complaint() {
    }

    public Complaint(Integer id, String text, ReservationEntity reservationEntity, User client, Boolean isResponded) {
        this.id = id;
        this.text = text;
        this.reservationEntity = reservationEntity;
        this.client = client;
        this.isResponded = isResponded;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ReservationEntity getReservationEntity() {
        return reservationEntity;
    }

    public void setReservationEntity(ReservationEntity reservationEntity) {
        this.reservationEntity = reservationEntity;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Boolean getResponded() {
        return isResponded;
    }

    public void setResponded(Boolean responded) {
        isResponded = responded;
    }
}
