package com.example.fishingbooker.Model;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name="entity_id", referencedColumnName = "entity_id")
    private ReservationEntity reservationEntity;

    @Column(name = "path")
    private String path;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    public Image() {
    }

    public Image(Integer id, ReservationEntity reservationEntity, String path) {
        this.id = id;
        this.reservationEntity = reservationEntity;
        this.path = path;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
