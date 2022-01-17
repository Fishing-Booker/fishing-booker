package com.example.fishingbooker.DTO;

public class ReportAdminDTO {
    private Integer id;
    private Integer reservationId;
    private String text;
    private boolean forPenalty;
    private boolean skippedReservation;
    private boolean isPenaltyRejected;

    public ReportAdminDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isForPenalty() {
        return forPenalty;
    }

    public void setForPenalty(boolean forPenalty) {
        this.forPenalty = forPenalty;
    }

    public boolean isSkippedReservation() {
        return skippedReservation;
    }

    public void setSkippedReservation(boolean skippedReservation) {
        this.skippedReservation = skippedReservation;
    }

    public boolean isPenaltyRejected() {
        return isPenaltyRejected;
    }

    public void setPenaltyRejected(boolean penaltyRejected) {
        isPenaltyRejected = penaltyRejected;
    }
}
