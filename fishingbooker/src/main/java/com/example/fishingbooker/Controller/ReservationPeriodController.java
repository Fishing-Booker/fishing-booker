package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.reservationPeriod.AddReservationPeriodDTO;
import com.example.fishingbooker.DTO.reservationPeriod.GetReservationPeriodDTO;
import com.example.fishingbooker.DTO.reservationPeriod.ReservationPeriodDTO;
import com.example.fishingbooker.IService.IReservationPeriodService;
import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.Service.ReservationEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('LODGEOWNER') || hasRole('SHIPOWNER') || hasRole('INSTRUCTOR')")
    public ResponseEntity<String> addReservationPeriod(@RequestBody AddReservationPeriodDTO period){
        periodService.save(period);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/freePeriods/{owner}/{entity}")
    public List<ReservationPeriodDTO> getFreePeriods(@PathVariable Integer owner, @PathVariable String entity){
        ReservationEntity resEntity = entityService.findOwnerEntityByName(entity, owner);
        return periodService.findFreePeriods(resEntity.getId());
    }

    @GetMapping("/allFreePeriods/{owner}/{entity}")
    @PreAuthorize("hasRole('LODGEOWNER') || hasRole('SHIPOWNER') || hasRole('INSTRUCTOR')")
    public List<ReservationPeriodDTO> getAllFreePeriods(@PathVariable Integer owner, @PathVariable Integer entity){
        return periodService.findAllFreePeriods(entity);
    }

    @PostMapping("/availablePeriods")
    @PreAuthorize("hasRole('CLIENT')")
    public List<ReservationPeriodDTO> getAvailablePeriods(@RequestBody ReservationPeriodDTO dto) {
        return periodService.getAvailablePeriods(dto.getEntityId(), dto.getStartDate(), dto.getEndDate());
    }

    @GetMapping("/freeOwnerAndShipPeriods/{owner}/{entity}")
    public List<ReservationPeriodDTO> getShipAndOwnerFreePeriods(@PathVariable Integer owner, @PathVariable String entity){
        ReservationEntity resEntity = entityService.findOwnerEntityByName(entity, owner);
        return periodService.findFreePeriodsForShipAndOwner(resEntity.getId(), owner);
    }

    @GetMapping("/entityPeriods/{entity}")
    public List<GetReservationPeriodDTO> getEntityPeriods(@PathVariable Integer entity){
        return periodService.findEntityPeriods(entity);
    }

    @DeleteMapping("/deletePeriod/{id}/{entity}")
    @PreAuthorize("hasRole('LODGEOWNER') || hasRole('SHIPOWNER') || hasRole('INSTRUCTOR')")
    public void deletePeriod(@PathVariable Integer id, @PathVariable Integer entity){
        periodService.deletePeriod(entity, id);
    }

    @GetMapping("/checkIsPeriodFree/{id}")
    @PreAuthorize("hasRole('LODGEOWNER') || hasRole('SHIPOWNER') || hasRole('INSTRUCTOR')")
    public boolean checkIsPeriodAvailable(@PathVariable Integer id){
        return periodService.isPeriodAvailable(id);
    }



}
