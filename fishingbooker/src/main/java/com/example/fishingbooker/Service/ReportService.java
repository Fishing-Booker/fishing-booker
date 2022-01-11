package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.ReportDTO;
import com.example.fishingbooker.IRepository.IReportRepository;
import com.example.fishingbooker.IRepository.IReservationEntityRepository;
import com.example.fishingbooker.IRepository.IReservationRepository;
import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.IService.IReportService;
import com.example.fishingbooker.Model.Report;
import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ReportService implements IReportService {

    @Autowired
    IReportRepository reportRepository;

    @Autowired
    IReservationRepository reservationRepository;

    @Override
    public void addReport(ReportDTO dto) {
        Report report = new Report();
        report.setText(dto.getText());
        report.setReservation(reservationRepository.findReservationById(dto.getReservationId()));
        report.setForPenalty(dto.isForPenalty() || dto.isSkippedReservation());
        report.setPenaltyGiven(dto.isSkippedReservation());
        reportRepository.save(report);
    }
}
