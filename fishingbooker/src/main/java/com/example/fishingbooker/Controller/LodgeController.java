package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.LodgeDTO;
import com.example.fishingbooker.DTO.UpdateLodgeDTO;
import com.example.fishingbooker.DTO.lodge.LodgeInfoDTO;
import com.example.fishingbooker.DTO.lodge.ReservationDateDTO;
import com.example.fishingbooker.Enum.BedroomType;
import com.example.fishingbooker.IService.*;
import com.example.fishingbooker.Model.*;
import com.example.fishingbooker.Service.LodgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/lodges", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class LodgeController {

    @Autowired
    private IReservationEntityService entityService;

    @Autowired
    private LodgeService lodgeService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IBedroomService bedroomService;

    @Autowired
    private ILocationService locationService;

    @GetMapping("/ownerLodges/{id}")
    public List<Lodge> getOwnerLodges(@PathVariable Integer id){
        return lodgeService.findOwnerLodges(id);
    }

    @DeleteMapping("/deleteLodge/{id}")
    public ResponseEntity<Lodge> deleteLodge(@PathVariable Integer id){
        lodgeService.deleteLodge(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addLodge")
    public ResponseEntity<Lodge> addLodge(@RequestBody LodgeDTO lodgeDTO){

        User owner = userService.findUserById(lodgeDTO.getOwner());

        Location location = addLocation(lodgeDTO.getAddress(), lodgeDTO.getCity(), lodgeDTO.getCountry());

        Integer id = 0;
        if(entityService.findEntities().size() == 0) {
            id = 1;
        } else {
            id = entityService.setId();
        }

        Lodge lodge = new Lodge(id, owner, lodgeDTO.getName(), location, lodgeDTO.getDescription(),
                "", "", 0.0, lodgeDTO.getMaxPersons(), new ArrayList<>());

        lodgeService.save(lodge);

        addBedrooms(lodge, lodgeDTO.getOneBed(), lodgeDTO.getTwoBed(), lodgeDTO.getThreeBed(), lodgeDTO.getFourBed());

        return new ResponseEntity<>(HttpStatus.CREATED);//201
    }

    @GetMapping()
    public List<LodgeInfoDTO> getAll() {
        return lodgeService.getAll();
    }

    @GetMapping("/search")
    public List<LodgeInfoDTO> getSearchResults(@RequestParam(required = false) String name, @RequestParam(required = false) String letter, @RequestParam(required = false) String location) {
        return lodgeService.search(name, letter, location);
    }

    @PostMapping("/byDate")
    public List<LodgeInfoDTO> getLodgesByReservationDate(@RequestBody ReservationDateDTO dto) {
        return lodgeService.getByReservationDate(dto.getDate());
    }

    @GetMapping("/lodge/{id}")
    public Lodge findLodge(@PathVariable Integer id){
        return lodgeService.findById(id);
    }

    @GetMapping("/lodgeRules/{id}")
    public List<String> findLodgeRules(@PathVariable Integer id){
        return lodgeService.findLodgeRules(id);
    }

    @PutMapping("/addRule/{id}")
    public ResponseEntity<String> addRule(@RequestBody String rule, @PathVariable Integer id){
        lodgeService.addRule(rule, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteRule/{id}/{index}")
    public ResponseEntity<String> deleteRule(@PathVariable Integer index, @PathVariable Integer id){
        lodgeService.deleteRule(index, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateLodge/{id}")
    public ResponseEntity<Lodge> updateLodge(@RequestBody UpdateLodgeDTO lodge, @PathVariable Integer id){
        lodgeService.updateLodge(lodge, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/letters")
    public List<String> getFirstLetters() {
        return lodgeService.getFirstLetters();
    }

    private Location addLocation(String address, String city, String country){
        Location location = new Location();
        location.setAddress(address);
        location.setCity(city);
        location.setCountry(country);

        return locationService.save(location);
    }

    @GetMapping("/lodge")
    public LodgeInfoDTO getById(@RequestParam Integer id) {
        return lodgeService.getById(id);
    }

    @GetMapping("/lodgeNames/{id}")
    public List<String> getOwnerLodgeNames(@PathVariable Integer id){
        return lodgeService.getOwnerLodgeNames(id);
    }

    private void addBedrooms(Lodge lodge, String oneBed, String twoBed, String threeBed, String fourBed){
        Bedroom bedroom1 = new Bedroom();
        bedroom1.setBedroomType(BedroomType.oneBed);
        bedroom1.setRoomNumber(Integer.parseInt(oneBed));
        bedroom1.setLodge(lodge);
        bedroomService.save(bedroom1);

        Bedroom bedroom2 = new Bedroom();
        bedroom2.setBedroomType(BedroomType.twoBed);
        bedroom2.setRoomNumber(Integer.parseInt(twoBed));
        bedroom2.setLodge(lodge);
        bedroomService.save(bedroom2);

        Bedroom bedroom3 = new Bedroom();
        bedroom3.setBedroomType(BedroomType.threeBed);
        bedroom3.setRoomNumber(Integer.parseInt(threeBed));
        bedroom3.setLodge(lodge);
        bedroomService.save(bedroom3);

        Bedroom bedroom4 = new Bedroom();
        bedroom4.setBedroomType(BedroomType.fourBed);
        bedroom4.setRoomNumber(Integer.parseInt(fourBed));
        bedroom4.setLodge(lodge);
        bedroomService.save(bedroom4);
    }
}
