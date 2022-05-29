package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.ReportAdminDTO;
import com.example.fishingbooker.DTO.ReportDTO;
import com.example.fishingbooker.IService.IPenaltyService;
import com.example.fishingbooker.IService.IReportService;
import com.example.fishingbooker.Model.Shio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reports", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class ReportController {

    @Autowired
    IReportService reportService;

    @Autowired
    IPenaltyService penaltyService;

    @PostMapping("/addReport")
    @PreAuthorize("hasRole('LODGEOWNER') || hasRole('SHIPOWNER') || hasRole('INSTRUCTOR')")
    public ResponseEntity<Shio> addReport(@RequestBody ReportDTO report){
        reportService.addReport(report);
        if(report.isSkippedReservation()){
            penaltyService.addSkippedReservationPenalty(report.getReservationId());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getReportsForAdmin")
    public List<ReportAdminDTO> getReportsForAdmin(){
        return reportService.getReportsForAdmin();
    }

    @PutMapping("/approvePenalty/{id}")
    public void approvePenalty(@PathVariable Integer id){
        reportService.approvePenalty(id);
    }

    @PutMapping("/rejectPenalty/{id}")
    public void rejectPenalty(@PathVariable Integer id){
        reportService.rejectPenalty(id);
    }

    @GetMapping("/checkHasReservationReport/{id}")
    @PreAuthorize("hasRole('LODGEOWNER') || hasRole('SHIPOWNER') || hasRole('INSTRUCTOR')")
    public boolean hasReservationReport(@PathVariable Integer id){
        return reportService.hasReservationReport(id);
    }

}
