package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.ReportAdminDTO;
import com.example.fishingbooker.DTO.ReportDTO;
import com.example.fishingbooker.IRepository.*;
import com.example.fishingbooker.IService.IPenaltyService;
import com.example.fishingbooker.IService.IReportService;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Mapper.ReportMapper;
import com.example.fishingbooker.Model.Penalty;
import com.example.fishingbooker.Model.Report;
import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReportService implements IReportService {

    @Autowired
    IReportRepository reportRepository;

    @Autowired
    IReservationRepository reservationRepository;

    @Autowired
    IUserService userService;

    @Autowired
    IPenaltyRepository penaltyRepository;

    @Autowired
    IPenaltyService penaltyService;

    @Override
    public void addReport(ReportDTO dto) {
        Report report = new Report();
        report.setText(dto.getText());
        report.setReservation(reservationRepository.findReservationById(dto.getReservationId()));
        report.setForPenalty(dto.isForPenalty() || dto.isSkippedReservation());
        report.setPenaltyGiven(dto.isSkippedReservation());
        report.setPenaltyRejected(false);
        reportRepository.save(report);
    }

    @Override
    public List<ReportAdminDTO> getReportsForAdmin(){
        List<Report> reports = reportRepository.findAll();
        List<ReportAdminDTO> adminsReports = new ArrayList<>();
        for (Report r : reports) {
            if(r.forPenalty() && !r.isPenaltyGiven() && !r.isPenaltyRejected() ){
                adminsReports.add(ReportMapper.mapModelToAdminDTO(r));
            }
        }
        return adminsReports;
    }

    @Override
    public void approvePenalty(Integer reportId) {
        Report report = reportRepository.getById(reportId);
        reportRepository.approvePenalty(reportId);//azuriranje reporta
        penaltyService.addSkippedReservationPenalty(report.getReservation().getId());
        userService.sendEmailPenaltyGiven(report.getReservation().getClient(), report);//slanje mejla
        userService.sendEmailPenaltyGiven(report.getReservation().getReservationEntity().getOwner(), report);
    }

    @Override
    public void rejectPenalty(Integer reportId) {
        reportRepository.rejectPenalty(reportId);
    }

    @Override
    public boolean hasReservationReport(Integer reservationId){
        for (Report report : reportRepository.findAll()) {
            if(report.getReservation().getId() == reservationId){
                return true;
            }
        }
        return false;
    }
}
