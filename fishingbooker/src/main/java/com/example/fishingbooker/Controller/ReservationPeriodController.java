package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.reservationPeriod.ReservationPeriodDTO;
import com.example.fishingbooker.Service.ReservationPeriodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/periods", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class ReservationPeriodController {

    @Autowired
    private ReservationPeriodService periodService;

    @PostMapping("/addReservationPeriod")
    public ResponseEntity<String> addReservationPeriod(@RequestBody ReservationPeriodDTO period){
        periodService.save(period);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}