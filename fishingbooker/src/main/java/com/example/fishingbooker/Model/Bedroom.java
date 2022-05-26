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

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name="entity_id", referencedColumnName = "entity_id")
    private Shio lodge;

    public Bedroom() {
    }

    public Bedroom(Integer id, BedroomType bedroomType, Integer roomNumber, Shio lodge) {
        this.id = id;
        this.bedroomType = bedroomType;
        this.roomNumber = roomNumber;
        this.lodge = lodge;
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

    public ReservationEntity getLodge() {
        return lodge;
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

    public void setLodge(Shio lodge) {
        this.lodge = lodge;
    }
}
