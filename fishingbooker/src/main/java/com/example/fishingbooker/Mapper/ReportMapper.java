package com.example.fishingbooker.Mapper;

import com.example.fishingbooker.DTO.ReportAdminDTO;
import com.example.fishingbooker.DTO.ReportDTO;
import com.example.fishingbooker.Model.Report;

public class ReportMapper {
    public static ReportAdminDTO mapModelToAdminDTO(Report report){
        ReportAdminDTO dto = new ReportAdminDTO();
        dto.setReservationId(report.getReservation().getId());
        dto.setText(report.getText());
        dto.setForPenalty(report.forPenalty());
        dto.setSkippedReservation(report.isPenaltyGiven());
        dto.setId(report.getId());
        dto.setPenaltyRejected(report.isPenaltyRejected());
        return dto;
    }
}
