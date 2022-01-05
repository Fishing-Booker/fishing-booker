package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.AdventureDTO;
import com.example.fishingbooker.IService.IAdventureService;
import com.example.fishingbooker.IService.ILocationService;
import com.example.fishingbooker.IService.IReservationEntityService;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/adventures", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class AdventureController {
    @Autowired
    private IReservationEntityService entityService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ILocationService locationService;

    @Autowired
    private IAdventureService adventureService;

    @GetMapping("/instructorAdventures/{id}")
    public List<Adventure> getInstructorAdventures(@PathVariable Integer id) {
        return adventureService.findInstructorAdventures(id);
    }

    @PostMapping("/addAdventure")
    public ResponseEntity<Adventure> addAdventure(@RequestBody AdventureDTO adventureDTO) {
        ReservationEntity reservationEntity = new ReservationEntity();

        User user = userService.findUserById(adventureDTO.getOwner());
        reservationEntity.setOwner(user);

        reservationEntity.setName(adventureDTO.getName());

        Location location = addLocation(adventureDTO.getAddress(), adventureDTO.getCity(), adventureDTO.getCountry());
        reservationEntity.setLocation(location);

        reservationEntity.setDescription(adventureDTO.getDescription());
        reservationEntity.setRules("");
        reservationEntity.setCancelConditions("");
        reservationEntity.setDeleted(false);
        reservationEntity.setAverageGrade(0.0);
        reservationEntity.setImages(new ArrayList<>());

        Integer id = 0;
        if(entityService.findEntities().size() == 0) {
            id = 1;
        } else {
            id = entityService.setId();
        }

        Adventure adventure = new Adventure(id, reservationEntity.getOwner(), reservationEntity.getName(), location, reservationEntity.getDescription(), reservationEntity.getRules(),
                reservationEntity.getCancelConditions(), reservationEntity.getAverageGrade(), adventureDTO.getBiography(), adventureDTO.getMaxPersons(),
                reservationEntity.getImages());

        adventureService.save(adventure);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private Location addLocation(String address, String city, String country){
        Location location = new Location();
        location.setAddress(address);
        location.setCity(city);
        location.setCountry(country);

        return location = locationService.save(location);
    }

    @DeleteMapping("/deleteAdventure/{id}")
    public ResponseEntity<Adventure> deleteAdventure(@PathVariable Integer id) {
        adventureService.deleteAdventure(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
