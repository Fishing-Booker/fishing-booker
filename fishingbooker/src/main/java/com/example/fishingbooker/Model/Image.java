package com.example.fishingbooker.Model;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "imageSeqGen", sequenceName = "imageSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imageSeqGen")
    private Integer id;

    @ManyToOne(targetEntity = ReservationEntity.class, fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    //@JoinColumn(name="entity_id", referencedColumnName = "entity_id")
    private ReservationEntity reservationEntity;

    @Column(name = "base64", columnDefinition="TEXT")
    private String base64;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    public Image() {
    }

    public Image(Integer id, ReservationEntity reservationEntity, String base64) {
        this.id = id;
        this.reservationEntity = reservationEntity;
        this.base64 = base64;
        this.isDeleted = false;
    }

    public Image( ReservationEntity reservationEntity, String base64) {
        this.reservationEntity = reservationEntity;
        this.base64 = base64;
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

    public String getBase64() {
        return base64;
    }

    public void setBase64(String path) {
        this.base64 = path;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
