package com.example.fishingbooker.Model;

import javax.persistence.*;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "entity_id", referencedColumnName = "entity_id")
    private ReservationEntity reservationEntity;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "comment")
    private String comment;

    @Column(name = "is_approved")
    private boolean isApproved;

    public Rating() {
    }

    public Rating(Integer id, ReservationEntity reservationEntity, Integer grade, String comment) {
        this.id = id;
        this.reservationEntity = reservationEntity;
        this.grade = grade;
        this.comment = comment;
        this.isApproved = false;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
