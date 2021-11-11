package com.example.fishingbooker.Model;

import javax.persistence.*;

@Entity
@Table(name = "accommodation")
public class Accommodation {

    @Id
    @Column(name = "accomodation_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "entity_id", referencedColumnName = "entity_id")
    private ReservationEntity reservationEntity;

    @Column(name = "name")
    private String name;

    public Accommodation() {
    }

    public Accommodation(Integer id, ReservationEntity reservationEntity, String name) {
        this.id = id;
        this.reservationEntity = reservationEntity;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReservationEntity getReservationEntity() {
        return reservationEntity;
    }

    public void setReservationEntity(ReservationEntity reservationEntity) {
        this.reservationEntity = reservationEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
