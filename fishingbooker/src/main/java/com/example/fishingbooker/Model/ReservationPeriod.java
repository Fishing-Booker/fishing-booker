package com.example.fishingbooker.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservation_period")
@Inheritance(strategy = InheritanceType.JOINED)
public class ReservationPeriod {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(targetEntity = ReservationEntity.class, fetch = FetchType.LAZY, optional = false)
    //@JoinColumn(name = "entity_id", referencedColumnName = "entity_id")
    private ReservationEntity reservationEntity;

    public ReservationPeriod() {
    }

    public ReservationPeriod(Integer id, Date startDate, Date endDate, ReservationEntity reservationEntity) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reservationEntity = reservationEntity;
    }

    public ReservationPeriod(Date startDate, Date endDate, ReservationEntity reservationEntity) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.reservationEntity = reservationEntity;
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

    public ReservationEntity getReservationEntity() {
        return reservationEntity;
    }

    public void setReservationEntity(ReservationEntity reservationEntity) {
        this.reservationEntity = reservationEntity;
    }
}
