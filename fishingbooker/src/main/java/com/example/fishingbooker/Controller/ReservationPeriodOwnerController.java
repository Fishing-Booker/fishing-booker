package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.reservationPeriod.ReservationPeriodDTO;
import com.example.fishingbooker.DTO.reservationPeriodOwner.ReservationPeriodOwnerDTO;
import com.example.fishingbooker.IService.IReservationPeriodOwnerService;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/ownerPeriods", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class ReservationPeriodOwnerController {

    @Autowired
    private IReservationPeriodOwnerService reservationPeriodOwnerService;

    @Autowired
    private IUserService userService;

    @PostMapping("/addOwnerReservationPeriod")
    @PreAuthorize("hasRole('INSTRUCTOR') || hasRole('SHIPOWNER')")
    public ResponseEntity<String> addOwnerReservationPeriod(@RequestBody ReservationPeriodOwnerDTO reservationPeriod) {
        reservationPeriodOwnerService.save(reservationPeriod);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getOwnerReservationPeriods")
    @PreAuthorize("hasRole('INSTRUCTOR') || hasRole('SHIPOWNER')")
    public List<ReservationPeriodOwnerDTO> getOwnerReservationPeriods(Principal user) {
        User u = userService.findByUsername(user.getName());
        return reservationPeriodOwnerService.findAllPeriods(u.getId());
    }

    @PostMapping("/availablePeriods")
    public List<ReservationPeriodDTO> getAvailablePeriods(@RequestBody ReservationPeriodDTO dto) {
        return reservationPeriodOwnerService.getAvailablePeriods(dto.getEntityId(), dto.getStartDate(), dto.getEndDate());
    }
}
