package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.reservationPeriod.AddReservationPeriodDTO;
import com.example.fishingbooker.DTO.reservationPeriod.ReservationPeriodDTO;
import com.example.fishingbooker.IService.IReservationPeriodService;
import com.example.fishingbooker.Service.ReservationEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/periods", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class ReservationPeriodController {

    @Autowired
    private IReservationPeriodService periodService;

    @Autowired
    private ReservationEntityService entityService;

    @PostMapping("/addReservationPeriod")
    public ResponseEntity<String> addReservationPeriod(@RequestBody AddReservationPeriodDTO period){
        periodService.save(period);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/freePeriods/{id}")
    public List<ReservationPeriodDTO> getFreePeriods(@PathVariable Integer id){
        List<ReservationPeriodDTO> periods = periodService.findFreePeriods(id);
        for (ReservationPeriodDTO period : periods) {
            System.out.println(period.getStartDate());
            System.out.println(period.getEndDate());
        }
        System.out.println("\n");
        return periods;
    }

}
