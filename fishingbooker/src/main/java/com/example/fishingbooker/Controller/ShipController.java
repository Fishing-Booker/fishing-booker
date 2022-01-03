package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.ShipDTO;
import com.example.fishingbooker.IService.ILocationService;
import com.example.fishingbooker.IService.IReservationEntityService;
import com.example.fishingbooker.IService.IShipService;
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

@RestController
@RequestMapping(value = "/ships", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class ShipController {

    @Autowired
    private IShipService shipService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ILocationService locationService;

    @Autowired
    private IReservationEntityService entityService;

    @GetMapping("/ownerShips/{id}")
    public List<Ship> getOwnerShips(@PathVariable Integer id){
        return shipService.findOwnerShips(id);
    }

    @DeleteMapping("/deleteShip/{id}")
    public ResponseEntity<Lodge> deleteLodge(@PathVariable Integer id){
        shipService.deleteShip(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addShip")
    public ResponseEntity<Ship> addShip(@RequestBody ShipDTO shipDTO){

        Ship ship = new Ship();

        User owner = userService.findUserById(shipDTO.getOwner());
        ship.setOwner(owner);

        ship.setName(shipDTO.getName());

        Location location = addLocation(shipDTO.getAddress(), shipDTO.getCity(), shipDTO.getCountry());
        ship.setLocation(location);

        ship.setDescription(shipDTO.getDescription());
        ship.setRules("");
        ship.setCancelConditions("");
        ship.setDeleted(false);
        ship.setAverageGrade(0.0);
        ship.setImages(new ArrayList<>());

        ship.setShipType(shipDTO.getShipType());
        ship.setLength(shipDTO.getLength());
        ship.setEngineNumber(shipDTO.getEngineNumber());
        ship.setEnginePower(shipDTO.getEnginePower());
        ship.setMaxSpeed(shipDTO.getMaxSpeed());
        ship.setCapacity(shipDTO.getCapacity());

        Integer id;
        if(entityService.findEntities().size() == 0){
            id = 1;
        } else {
            id = entityService.findEntities().get(entityService.findEntities().size() - 1).getId() + 1;
        }

        ship.setId(id);
        shipService.save(ship);

        return new ResponseEntity<>(HttpStatus.CREATED);//201
    }

    private Location addLocation(String address, String city, String country){
        Location location = new Location();
        location.setAddress(address);
        location.setCity(city);
        location.setCountry(country);

        return locationService.save(location);
    }

}
