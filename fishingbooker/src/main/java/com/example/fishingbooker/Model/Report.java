package com.example.fishingbooker.Model;

import com.example.fishingbooker.Enum.ReportType;

import javax.persistence.*;

@Entity
@Table(name = "report")
@Inheritance(strategy = InheritanceType.JOINED)
public class Report {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private Reservation reservation;

    @Column(name = "text")
    private String text;

    @Column(name = "for_penalty")
    private boolean forPenalty;

    @Column(name = "is_penalty_given")
    private boolean isPenaltyGiven;

    @Column(name = "is_penalty_rejected")
    private boolean isPenaltyRejected;

    public Report() {
    }

    public Report(Integer id, Reservation reservation, String text, boolean forPenalty, boolean isPenaltyGiven, boolean isPenaltyRejected) {
        this.id = id;
        this.reservation = reservation;
        this.text = text;
        this.forPenalty = forPenalty;
        this.isPenaltyGiven = isPenaltyGiven;
        this.isPenaltyRejected = isPenaltyRejected;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean forPenalty() {
        return forPenalty;
    }

    public void setForPenalty(boolean forPenalty) {
        this.forPenalty = forPenalty;
    }

    public boolean isPenaltyGiven() {
        return isPenaltyGiven;
    }

    public void setPenaltyGiven(boolean penaltyGiven) {
        isPenaltyGiven = penaltyGiven;
    }

    public boolean isForPenalty() {
        return forPenalty;
    }

    public boolean isPenaltyRejected() {
        return isPenaltyRejected;
    }

    public void setPenaltyRejected(boolean penaltyRejected) {
        isPenaltyRejected = penaltyRejected;
    }
}
