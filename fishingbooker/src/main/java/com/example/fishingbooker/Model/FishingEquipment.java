package com.example.fishingbooker.Model;

import javax.persistence.*;

@Entity
@Table(name = "fishingEquipment")
public class FishingEquipment {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "fishingEquipmentSeqGen", sequenceName = "fishingEquipmentSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fishingEquipmentSeqGen")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = ReservationEntity.class, fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    //@JoinColumn(name = "entity_id", referencedColumnName = "entity_id")
    private ReservationEntity reservationEntity;

    @Column(name = "name")
    private String name;

    public FishingEquipment() {
    }

    public FishingEquipment(Integer id, ReservationEntity reservationEntity, String name) {
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
