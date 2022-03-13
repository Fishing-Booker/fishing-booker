package com.example.fishingbooker.Model;

import com.example.fishingbooker.Enum.ReservationType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservation")
@Inheritance(strategy = InheritanceType.JOINED)
public class Reservation {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(targetEntity = ReservationEntity.class, fetch = FetchType.LAZY, optional = false, cascade = {CascadeType.MERGE})
    //@JoinColumn(name = "entity_id", referencedColumnName = "entity_id")
    private ReservationEntity reservationEntity;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    //@JoinColumn(name="client_id", referencedColumnName = "user_id")
    private User client;

    @Column(name = "reservation_type")
    @Enumerated(EnumType.STRING)
    private ReservationType reservationType;

    @Column(name = "price")
    private double price;

    @Column(name = "max_persons")
    private Integer maxPersons;

    @Column(name = "additional_services")
    private String additionalServices;

    @Column(name = "regular_service")
    private String regularService;

    @Column(name = "is_booked")
    private boolean isBooked;

    public Reservation() {
    }

    public Reservation(Integer id, Date startDate, Date endDate, ReservationEntity reservationEntity, User client, ReservationType reservationType, double price, Integer maxPersons, String additionalServices, String regularService, boolean isBooked) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reservationEntity = reservationEntity;
        this.client = client;
        this.reservationType = reservationType;
        this.price = price;
        this.maxPersons = maxPersons;
        this.additionalServices = additionalServices;
        this.regularService = regularService;
        this.isBooked = isBooked;
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

    public String getRegularService() {
        return regularService;
    }

    public void setRegularService(String regularService) {
        this.regularService = regularService;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
