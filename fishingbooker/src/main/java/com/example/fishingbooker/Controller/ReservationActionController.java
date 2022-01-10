package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.reservationAction.AddReservationActionDTO;
import com.example.fishingbooker.DTO.reservationAction.ReservationActionDTO;
import com.example.fishingbooker.IService.IReservationActionService;
import com.example.fishingbooker.IService.ISubscriberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/actions", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class ReservationActionController {

    @Autowired
    IReservationActionService actionService;

    @Autowired
    ISubscriberService subscriberService;

    @PostMapping("/addAction")
    public ResponseEntity<String> addAction(@RequestBody AddReservationActionDTO action){
        actionService.save(action);
        subscriberService.sendEmailWithActionInfo(action);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/entityActions/{id}")
    public List<ReservationActionDTO> getEntityActions(@PathVariable Integer id){
        return actionService.findEntityActions(id);
    }

}
