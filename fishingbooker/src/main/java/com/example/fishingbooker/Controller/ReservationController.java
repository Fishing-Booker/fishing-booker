package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.reservation.AddReservationDTO;
import com.example.fishingbooker.DTO.reservation.ReservationDTO;
import com.example.fishingbooker.IService.IReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class ReservationController {

    @Autowired
    private IReservationService reservationService;

    @PostMapping("/addReservation")
    public ResponseEntity<String> addReservation(@RequestBody AddReservationDTO reservation){
        reservationService.save(reservation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
