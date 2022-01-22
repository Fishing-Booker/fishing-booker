package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.ReportAdminDTO;
import com.example.fishingbooker.DTO.ReportDTO;
import com.example.fishingbooker.Model.Report;

import java.util.List;

public interface IReportService {

    void addReport(ReportDTO report);

    List<ReportAdminDTO> getReportsForAdmin();

    void approvePenalty(Integer reportId);

    void rejectPenalty(Integer reportId);

    boolean hasReservationReport(Integer reservationId);

}
