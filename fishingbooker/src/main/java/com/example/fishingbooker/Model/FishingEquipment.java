package com.example.fishingbooker.Model;

import javax.persistence.*;

@Entity
@Table(name = "fishingEquipment")
public class FishingEquipment {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "entity_id", referencedColumnName = "entity_id")
    private ReservationEntity reservationEntity;

    @Column(name = "name")
    private String name;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    public FishingEquipment() {
    }

    public FishingEquipment(Integer id, ReservationEntity reservationEntity, String name) {
        this.id = id;
        this.reservationEntity = reservationEntity;
        this.name = name;
        this.isDeleted = false;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
