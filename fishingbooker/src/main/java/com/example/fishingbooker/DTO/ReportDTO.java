package com.example.fishingbooker.DTO;

public class ReportDTO {

    private Integer reservationId;
    private String text;
    private boolean forPenalty;
    private boolean skippedReservation;

    public ReportDTO() {
    }

    public ReportDTO(Integer reservationId, String text, boolean forPenalty, boolean skippedReservation) {
        this.reservationId = reservationId;
        this.text = text;
        this.forPenalty = forPenalty;
        this.skippedReservation = skippedReservation;
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
}
