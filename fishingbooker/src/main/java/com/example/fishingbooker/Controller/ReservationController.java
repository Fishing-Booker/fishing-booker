package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.ClientDTO;
import com.example.fishingbooker.DTO.lodge.ReservationDateDTO;
import com.example.fishingbooker.DTO.reservation.ActiveReservationDTO;
import com.example.fishingbooker.DTO.reservation.AddReservationDTO;
import com.example.fishingbooker.DTO.reservation.ClientReservationDTO;
import com.example.fishingbooker.DTO.reservation.ReservationDTO;
import com.example.fishingbooker.IService.IReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class ReservationController {

    @Autowired
    private IReservationService reservationService;

    @PostMapping("/addReservation")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> addReservation(@RequestBody AddReservationDTO reservation){
        reservationService.save(reservation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getEntityReservations/{id}")
    public List<ReservationDTO> getEntityReservations(@PathVariable Integer id){
        return reservationService.findEntityReservations(id);
    }

    @GetMapping("/getOwnerEntitiesReservations/{id}")
    public List<ReservationDTO> getOwnerEntitiesReservations(@PathVariable Integer id){
            return reservationService.findOwnerEntitiesReservations(id);
    }

    @GetMapping("/checkActiveReservations/{id}")
    public boolean checkActiveReservations(@PathVariable Integer id){
        return reservationService.checkActiveReservations(id);
    }

    @GetMapping("/getClientUsername/{name}/{id}")
    public String getClientUsername(@PathVariable String name, @PathVariable Integer id){
        return reservationService.getClientUsername(name, id);
    }

    @GetMapping("/{id}")
    public List<ReservationDTO> getClientReservations(@PathVariable Integer id) {
        return reservationService.getClientReservations(id);
    }

    @PostMapping("/makeReservation")
    public ResponseEntity<String> makeReservation(@RequestBody ClientReservationDTO dto) {
        reservationService.makeReservation(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/currentReservations")
    public List<ReservationDTO> getCurrentReservations(@RequestBody ReservationDateDTO dto) {
        return reservationService.getCurrentReservation(dto.getDate(), dto.getClientId());
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<String> cancelReservation(@PathVariable Integer id) {
        reservationService.cancelReservation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getClientsOfActiveReservations/{ownerId}") //id od instruktora
    public List<ClientDTO> getClientsOfActiveReservations(@PathVariable Integer ownerId) {
        return reservationService.getClientsOfActiveReservations(ownerId);
    }

    @GetMapping("/getEntityNameOfClientActiveReservation/{ownerId}/{clientName}")
    //@PreAuthorize("isAuthenticated()")
    public ActiveReservationDTO getEntityNameOfClientActiveReservation(@PathVariable Integer ownerId, @PathVariable String clientName){
        return reservationService.getEntityNameOfClientActiveReservation(ownerId, clientName);
    }

    @GetMapping("/getFutureOwnerEntitiesReservations/{id}")
    public List<ReservationDTO> getFutureOwnerEntitiesReservations(@PathVariable Integer id){
        return reservationService.findFutureOwnerEntitiesReservations(id);
    }

    @GetMapping("/getPastOwnerEntitiesReservations/{id}")
    public List<ReservationDTO> getPastOwnerEntitiesReservations(@PathVariable Integer id){
        return reservationService.findPastOwnerEntitiesReservations(id);
    }


}
