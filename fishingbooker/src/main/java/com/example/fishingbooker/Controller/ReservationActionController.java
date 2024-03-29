package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.reservationAction.AddReservationActionDTO;
import com.example.fishingbooker.DTO.reservationAction.MakeReservationDTO;
import com.example.fishingbooker.DTO.reservationAction.ReservationActionDTO;
import com.example.fishingbooker.IService.IReservationActionService;
import com.example.fishingbooker.IService.IReservationPeriodOwnerService;
import com.example.fishingbooker.IService.ISubscriberService;
import com.example.fishingbooker.Model.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
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
    @PreAuthorize("hasRole('LODGEOWNER') || hasRole('SHIPOWNER') || hasRole('INSTRUCTOR')")
    public ResponseEntity<String> addAction(@RequestBody AddReservationActionDTO action){
        actionService.save(action);
        subscriberService.sendEmailWithActionInfo(action);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addShipOwnerAction")
    public ResponseEntity<String> addShipOwnerAction(@RequestBody AddReservationActionDTO action){
        Reservation newAction = actionService.save(action);
        actionService.addShipOwnerAction(newAction, action.getOwner());
        subscriberService.sendEmailWithActionInfo(action);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/entityActions/{id}")
    public List<ReservationActionDTO> getEntityActions(@PathVariable Integer id){
        return actionService.findEntityActions(id);
    }

    @PostMapping("/makeReservation")
    @PreAuthorize("hasRole('CLIENT') || hasRole('LODGEOWNER') || hasRole('SHIPOWNER') || hasRole('INSTRUCTOR')")
    public ResponseEntity<String> makeReservation(@RequestBody MakeReservationDTO dto) throws MessagingException, UnsupportedEncodingException {
        System.out.println(dto.getActionId());
        System.out.println(dto.getClientId());
        actionService.makeReservation(dto.getActionId(), dto.getClientId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/available/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public List<ReservationActionDTO> getAvailableActions(@PathVariable Integer id) {
        return actionService.getAvailableActions(id);
    }

    @DeleteMapping("/deleteAction/{id}")
    public ResponseEntity<String> deleteAction(@PathVariable Integer id) {
        actionService.deleteAction(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
