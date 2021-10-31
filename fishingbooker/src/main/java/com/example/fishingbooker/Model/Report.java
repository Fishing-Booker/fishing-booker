package com.example.fishingbooker.Model;

import com.example.fishingbooker.Enum.ReportType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "report")
public class Report {

    @Id
    private int id;
    private int reservationId;
    private String description;
    private ReportType reportType;

    public Report() {
    }

    public Report(int id, int reservationId, String description, ReportType reportType) {
        this.id = id;
        this.reservationId = reservationId;
        this.description = description;
        this.reportType = reportType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }
}
