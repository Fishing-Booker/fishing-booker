package com.example.fishingbooker.Model;

import com.example.fishingbooker.Enum.BedroomType;

import javax.persistence.*;

@Entity
@Table(name = "bedroom")
public class Bedroom {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bedroom_type")
    private BedroomType bedroomType;

    @Column(name = "room_number")
    private Integer roomNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name="entity_id", referencedColumnName = "entity_id")
    private ReservationEntity reservationEntity;

    public Bedroom() {
    }

    public Bedroom(Integer id, BedroomType bedroomType, Integer roomNumber, ReservationEntity lodge) {
        this.id = id;
        this.bedroomType = bedroomType;
        this.roomNumber = roomNumber;
        this.reservationEntity = lodge;
    }

    public Integer getId() {
        return id;
    }

    public BedroomType getBedroomType() {
        return bedroomType;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public ReservationEntity getReservationEntity() {
        return reservationEntity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBedroomType(BedroomType bedroomType) {
        this.bedroomType = bedroomType;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setReservationEntity(ReservationEntity lodge) {
        this.reservationEntity = lodge;
    }
}
