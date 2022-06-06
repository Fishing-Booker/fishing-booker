package com.example.fishingbooker.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservation_period_owner")
@Inheritance(strategy = InheritanceType.JOINED)
public class ReservationPeriodOwner {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "reservationPeriodOwnerSeqGen", sequenceName = "reservationPeriodOwnerSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservationPeriodOwnerSeqGen")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, optional = false, cascade = {CascadeType.MERGE})
    //@JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    private User owner;

    public ReservationPeriodOwner() {
    }

    public ReservationPeriodOwner(Date startDate, Date endDate, User owner) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.owner = owner;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
