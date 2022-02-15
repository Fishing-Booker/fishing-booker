package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.ReservationEntityDTO;
import com.example.fishingbooker.IService.ILocationService;
import com.example.fishingbooker.IService.IReservationEntityService;
import com.example.fishingbooker.Model.Location;
import com.example.fishingbooker.Model.ReservationEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/locations", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class LocationController {

    @Autowired
    ILocationService locationService;

    @Autowired
    IReservationEntityService entityService;

    @GetMapping("/entityLocation/{id}")
    public Location getEntityLocation(@PathVariable Integer id){
        ReservationEntityDTO entity = entityService.findEntityById(id);
        return locationService.getLocationById(entity.getLocationId());
    }

}
