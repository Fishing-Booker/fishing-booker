package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.ship.AddShipDTO;
import com.example.fishingbooker.DTO.ship.UpdateShipDTO;
import com.example.fishingbooker.DTO.lodge.ReservationDateDTO;
import com.example.fishingbooker.DTO.ship.ShipDTO;
import com.example.fishingbooker.DTO.ship.ShipInfoDTO;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/ships", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class ShipController {

    @Autowired
    IShipService shipService;

    @Autowired
    IUserService userService;

    @Autowired
    ILocationService locationService;

    @Autowired
    IReservationEntityService entityService;

    @GetMapping("/ownerShips/{id}")
    public List<ShipDTO> getOwnerShips(@PathVariable Integer id) throws IOException {
        return shipService.findOwnerShips(id);
    }

    @DeleteMapping("/deleteShip/{id}")
    @PreAuthorize("hasRole('SHIPOWNER')")
    public ResponseEntity<Lodge> deleteLodge(@PathVariable Integer id){
        shipService.deleteShip(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addShip")
    @PreAuthorize("hasRole('SHIPOWNER')")
    public ResponseEntity<Ship> addShip(@RequestBody AddShipDTO shipDTO){

        Ship ship = new Ship();

        User owner = userService.findUserById(shipDTO.getOwner());
        ship.setOwner(owner);

        ship.setName(shipDTO.getName());

        Location location = addLocation();
        ship.setLocation(location);

        ship.setDescription(shipDTO.getDescription());
        ship.setRules("");
        ship.setCancelConditions(0.0);
        ship.setDeleted(false);
        ship.setAverageGrade(0.0);
        ship.setMaxPersons(shipDTO.getCapacity());
        ship.setNavigationEquipment("");

        ship.setShipType(shipDTO.getShipType());
        ship.setLength(shipDTO.getLength());
        ship.setEngineNumber(shipDTO.getEngineNumber());
        ship.setEnginePower(shipDTO.getEnginePower());
        ship.setMaxSpeed(shipDTO.getMaxSpeed());

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

    private Location addLocation(){
        Location location = new Location();
        location.setAddress("");
        location.setCity("");
        location.setCountry("");

        return locationService.save(location);
    }

    @GetMapping("/ship/{id}")
    @PreAuthorize("hasRole('SHIPOWNER')")
    public Ship findShip(@PathVariable Integer id){
        return shipService.findById(id);
    }

    @PutMapping("/updateShip/{id}")
    @PreAuthorize("hasRole('SHIPOWNER')")
    public ResponseEntity<Ship> updateShip(@RequestBody UpdateShipDTO dto, @PathVariable Integer id){
        shipService.updateShip(dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/shipRules/{id}")
    public List<String> findShipRules(@PathVariable Integer id){
        return shipService.findShipRules(id);
    }

    @PutMapping("/addRule/{id}")
    @PreAuthorize("hasRole('SHIPOWNER')")
    public ResponseEntity<String> addRule(@RequestBody String rule, @PathVariable Integer id){
        shipService.addRule(rule, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteRule/{id}/{index}")
    @PreAuthorize("hasRole('SHIPOWNER')")
    public ResponseEntity<String> deleteRule(@PathVariable Integer index, @PathVariable Integer id){
        shipService.deleteRule(index, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public List<ShipInfoDTO> getAll() {
        return shipService.getAll();
    }

    @GetMapping("/letters")
    public List<String> getFirstLetters() {
        return shipService.getFirstLetters();
    }

    @GetMapping("/search")
    public List<ShipInfoDTO> getSearchResults(@RequestParam(required = false) String name, @RequestParam(required = false) String letter, @RequestParam(required = false) String location,
                                              @RequestParam(required = false) Integer grade) {
        return shipService.searchAndFilter(name, letter, location, grade);
    }

    @GetMapping("/ship")
    @PreAuthorize("hasRole('CLIENT')")
    public ShipInfoDTO getById(@RequestParam Integer id) {
        return shipService.getById(id);
    }

    @PostMapping("/byDate")
    public List<ShipInfoDTO> getShipsByReservationDate(@RequestBody ReservationDateDTO dto) {
        return shipService.getByReservationDate(dto.getDate());
    }

    @GetMapping("/shipNavEq/{id}")
    public List<String> findShipNavEquipment(@PathVariable Integer id){
        return shipService.findShipNavEquipment(id);
    }

    @PutMapping("/addNavEq/{id}")
    @PreAuthorize("hasRole('SHIPOWNER')")
    public ResponseEntity<String> addShipNavEquipment(@RequestBody String equipment, @PathVariable Integer id){
        shipService.addNavEquipment(equipment, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteNavEq/{id}/{index}")
    @PreAuthorize("hasRole('SHIPOWNER')")
    public ResponseEntity<String> deleteShipNavEquipment(@PathVariable Integer index, @PathVariable Integer id){
        shipService.deleteNavEquipment(index, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/shipNames/{id}")
    @PreAuthorize("hasRole('SHIPOWNER')")
    public List<String> getOwnerShipNames(@PathVariable Integer id){
        return shipService.getOwnerShipNames(id);
    }

    @GetMapping("/searchShip")
    @PreAuthorize("hasRole('SHIPOWNER')")
    public List<ShipDTO> getSearchedLodges(@RequestParam(required = false) String name, @RequestParam(required = false) Integer owner) throws IOException {
        return shipService.searchShipsByName(name, owner);
    }

    @GetMapping("/sort")
    public List<ShipInfoDTO> sortShips(@RequestParam String type) {
        return shipService.sortShips(type);
    }
}
