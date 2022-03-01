package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.ClientDTO;
import com.example.fishingbooker.DTO.lodge.LodgeDTO;
import com.example.fishingbooker.DTO.lodge.ReservationDateDTO;
import com.example.fishingbooker.DTO.reservation.*;
import com.example.fishingbooker.IService.ICanceledReservationService;
import com.example.fishingbooker.IService.IReservationPeriodOwnerService;
import com.example.fishingbooker.IService.IReservationService;
import com.example.fishingbooker.Model.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class ReservationController {

    @Autowired
    private IReservationService reservationService;

    @Autowired
    private IReservationPeriodOwnerService ownerService;

    @Autowired
    private ICanceledReservationService canceledReservationService;

    @PostMapping("/addReservation")
    @PreAuthorize("hasRole('LODGEOWNER') || hasRole('SHIPOWNER') || hasRole('INSTRUCTOR')")
    public ResponseEntity<String> addReservation(@RequestBody OwnerReservationDTO reservation){
        reservationService.makeReservationOwner(reservation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addOwnerReservation/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> addOwnerReservation(@RequestBody OwnerReservationDTO reservation, @PathVariable Integer id){
        Reservation newReservation = reservationService.makeReservationOwner(reservation);
        ownerService.addShipOwnerReservation(newReservation, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/shipOwnerReservations/{id}")
    public List<ReservationDTO> getShipOwnerReservations(@PathVariable Integer id){
        return ownerService.findShipOwnerReservations(id);
    }

    @GetMapping("/getEntityReservations/{id}")
    @PreAuthorize("hasRole('LODGEOWNER') || hasRole('SHIPOWNER') || hasRole('INSTRUCTOR')")
    public List<ReservationDTO> getEntityReservations(@PathVariable Integer id){
        return reservationService.findEntityReservations(id);
    }

    @GetMapping("/getOwnerEntitiesReservations/{id}")
    public List<ReservationDTO> getOwnerEntitiesReservations(@PathVariable Integer id){
            return reservationService.findOwnerEntitiesReservations(id);
    }

    @GetMapping("/checkActiveReservations/{id}")
    @PreAuthorize("hasRole('LODGEOWNER') || hasRole('SHIPOWNER') || hasRole('INSTRUCTOR')")
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

    @GetMapping("/{id}/sort")
    public List<ReservationDTO> sortClientReservations(@RequestParam String type, @PathVariable Integer id) {
        return reservationService.sortClientReservations(type, id);
    }

    @PostMapping("/makeReservation")
    public ResponseEntity<String> makeReservation(@RequestBody ClientReservationDTO dto) {
        if (canceledReservationService.doesCanceledReservationExist(dto.getClientId(), dto.getEntityId(), dto.getStartDate(), dto.getEndDate()))
            return new ResponseEntity<>(HttpStatus.FOUND);
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
    @PreAuthorize("hasRole('LODGEOWNER') || hasRole('SHIPOWNER') || hasRole('INSTRUCTOR')")
    public List<ClientDTO> getClientsOfActiveReservations(@PathVariable Integer ownerId) {
        return reservationService.getClientsOfActiveReservations(ownerId);
    }

    @GetMapping("/getEntityNameOfClientActiveReservation/{ownerId}/{clientName}")
    @PreAuthorize("isAuthenticated()")
    public ActiveReservationDTO getEntityNameOfClientActiveReservation(@PathVariable Integer ownerId, @PathVariable String clientName){
        return reservationService.getEntityNameOfClientActiveReservation(ownerId, clientName);
    }

    @GetMapping("/getFutureOwnerEntitiesReservations/{id}")
    @PreAuthorize("hasRole('LODGEOWNER') || hasRole('SHIPOWNER') || hasRole('INSTRUCTOR')")
    public List<ReservationDTO> getFutureOwnerEntitiesReservations(@PathVariable Integer id){
        return reservationService.findFutureOwnerEntitiesReservations(id);
    }

    @GetMapping("/getPastOwnerEntitiesReservations/{id}")
    @PreAuthorize("hasRole('LODGEOWNER') || hasRole('SHIPOWNER') || hasRole('INSTRUCTOR')")
    public List<ReservationDTO> getPastOwnerEntitiesReservations(@PathVariable Integer id){
        return reservationService.findPastOwnerEntitiesReservations(id);
    }

    @GetMapping("/getOwnerReservations/{id}")
    public List<ReservationForCalendarDTO> getOwnerReservations(@PathVariable Integer id){
        return reservationService.findOwnerReservations(id);
    }

    @GetMapping("/checkEntityFutureReservations/{id}")
    @PreAuthorize("hasRole('LODGEOWNER') || hasRole('SHIPOWNER') || hasRole('INSTRUCTOR')")
    public boolean hasEntityFutureReservations(@PathVariable Integer id){
        return reservationService.hasEntityFutureReservations(id);
    }

    @GetMapping("/searchClients")
    @PreAuthorize("hasRole('LODGEOWNER') || hasRole('SHIPOWNER') || hasRole('INSTRUCTOR')")
    public List<ReservationDTO> searchClients(@RequestParam(required = false) String username, @RequestParam(required = false) Integer owner) {
        return reservationService.searchClients(username, owner);
    }

    @GetMapping("/getEntityNamesOfActiveReservations/{id}")
    public List<String> getEntityNamesOfActiveReservations(@PathVariable Integer id){
        return reservationService.getEntityNamesOfActiveReservations(id);
    }

    @GetMapping("/getReservationReportAdmin")
    @PreAuthorize("isAuthenticated()")
    public List<ReservationReportDTO> getReservationReportAdmin() {
        return reservationService.getReservationReport();
    }
}
