package com.example.fishingbooker.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "canceled_reservations")
public class CanceledReservation {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @OneToOne(targetEntity = User.class, optional = false, cascade = {CascadeType.ALL})
    //@JoinColumn(name = "client_id", referencedColumnName = "user_id")
    private User client;

    @OneToOne(targetEntity = ReservationEntity.class, optional = false, cascade = {CascadeType.MERGE})
    //@JoinColumn(name = "entity_id", referencedColumnName = "entity_id")
    private ReservationEntity entity;

    public CanceledReservation() {
    }

    public CanceledReservation(Date startDate, Date endDate, User client, ReservationEntity entity) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.client = client;
        this.entity = entity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public ReservationEntity getEntity() {
        return entity;
    }

    public void setEntity(ReservationEntity entity) {
        this.entity = entity;
    }
}
