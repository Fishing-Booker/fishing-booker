package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.ReportDTO;
import com.example.fishingbooker.IService.IPenaltyService;
import com.example.fishingbooker.IService.IReportService;
import com.example.fishingbooker.Model.Lodge;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Lodge> addReport(@RequestBody ReportDTO report){
        reportService.addReport(report);
        if(report.isSkippedReservation()){
            penaltyService.addSkippedReservationPenalty(report.getReservationId());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
