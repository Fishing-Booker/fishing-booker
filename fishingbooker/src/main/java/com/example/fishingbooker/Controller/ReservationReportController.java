package com.example.fishingbooker.Controller;

import com.example.fishingbooker.IService.IReservationReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/reservationReports", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class ReservationReportController {

    @Autowired
    private IReservationReportService reservationReportService;

    @GetMapping("/monthly/{year}/{owner}")
    public ResponseEntity<int[]> getMonthlyReport(@PathVariable int year, @PathVariable int owner)  {
        return new ResponseEntity<>(reservationReportService.getWeeklyReport(year, owner), HttpStatus.OK);
    }

    @GetMapping("/forLastFiveYears/{year}/{owner}")
    public ResponseEntity<int[]> getReportForLastFiveYears(@PathVariable int year, @PathVariable int owner)  {
        return new ResponseEntity<>(reservationReportService.getReportForLastFiveYears(year, owner), HttpStatus.OK);
    }

    @GetMapping("/weekly/{month}/{year}/{owner}")
    public ResponseEntity<int[]> getWeeklyReport(@PathVariable int month, @PathVariable int year, @PathVariable int owner)  {
        return new ResponseEntity<>(reservationReportService.getWeeklyReport(month, year, owner), HttpStatus.OK);
    }

    @GetMapping("/salary/{year}/{owner}")
    public ResponseEntity<double[]> getSalaryYearReport(@PathVariable int year, @PathVariable int owner)  {
        return new ResponseEntity<>(reservationReportService.getSalaryYearReport(year, owner), HttpStatus.OK);
    }

}
