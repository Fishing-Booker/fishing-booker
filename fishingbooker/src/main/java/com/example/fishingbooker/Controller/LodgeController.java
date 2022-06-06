package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.lodge.AddLodgeDTO;
import com.example.fishingbooker.DTO.lodge.UpdateLodgeDTO;
import com.example.fishingbooker.DTO.lodge.LodgeDTO;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/lodges", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class LodgeController {

    @Autowired
    IReservationEntityService entityService;

    @Autowired
    LodgeService lodgeService;

    @Autowired
    IUserService userService;

    @Autowired
    IBedroomService bedroomService;

    @Autowired
    ILocationService locationService;

    @GetMapping("/ownerLodges/{id}")
    @PreAuthorize("hasRole('LODGEOWNER')")
    public List<LodgeDTO> getOwnerLodges(@PathVariable Integer id) throws IOException {
        return lodgeService.findOwnerLodges(id);
    }

    @DeleteMapping("/deleteLodge/{id}")
    @PreAuthorize("hasRole('LODGEOWNER')")
    public ResponseEntity<Lodge> deleteLodge(@PathVariable Integer id){
        lodgeService.deleteLodge(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addLodge")
    @PreAuthorize("hasRole('LODGEOWNER')")
    public ResponseEntity<Lodge> addLodge(@RequestBody AddLodgeDTO lodgeDTO){

        User owner = userService.findUserById(lodgeDTO.getOwner());

        Location location = addLocation();

        Integer id = 0;
        if(entityService.findEntities().size() == 0) {
            id = 1;
        } else {
            id = entityService.setId();
        }

        Lodge lodge = new Lodge(owner, lodgeDTO.getName(), location, lodgeDTO.getDescription(),
                "", lodgeDTO.getCancelConditions(), 0.0, lodgeDTO.getMaxPersons(), new ArrayList<>());

        lodge = lodgeService.save(lodge);

        addBedrooms(lodge, lodgeDTO.getOneBed(), lodgeDTO.getTwoBed(), lodgeDTO.getThreeBed(), lodgeDTO.getFourBed());

        return new ResponseEntity<>(HttpStatus.CREATED);//201
    }

    @GetMapping()
    public List<LodgeInfoDTO> getAll() {
        return lodgeService.getAll();
    }

    @GetMapping("/search")
    public List<LodgeInfoDTO> getSearchResults(@RequestParam(required = false) String name, @RequestParam(required = false) String letter, @RequestParam(required = false) String location,
                                               @RequestParam(required = false) Integer grade, @RequestParam(required = false) String sortType) {
        return lodgeService.search(name, letter, location, grade, sortType);
    }

    @GetMapping("/searchLodge")
    @PreAuthorize("hasRole('LODGEOWNER')")
    public List<LodgeDTO> getSearchedLodges(@RequestParam(required = false) String name, @RequestParam(required = false) Integer owner) throws IOException {
        return lodgeService.searchLodgesByName(name, owner);
    }

    @PostMapping("/byDate")
    public List<LodgeInfoDTO> getLodgesByReservationDate(@RequestBody ReservationDateDTO dto) {
        return lodgeService.getByReservationDate(dto.getDate());
    }

    @GetMapping("/lodge/{id}")
    @PreAuthorize("hasRole('LODGEOWNER')")
    public Lodge findLodge(@PathVariable Integer id){
        return lodgeService.findById(id);
    }

    @GetMapping("/lodgeRules/{id}")
    public List<String> findLodgeRules(@PathVariable Integer id){
        return lodgeService.findLodgeRules(id);
    }

    @PutMapping("/addRule/{id}")
    @PreAuthorize("hasRole('LODGEOWNER')")
    public ResponseEntity<String> addRule(@RequestBody String rule, @PathVariable Integer id){
        lodgeService.addRule(rule, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteRule/{id}/{index}")
    @PreAuthorize("hasRole('LODGEOWNER')")
    public ResponseEntity<String> deleteRule(@PathVariable Integer index, @PathVariable Integer id){
        lodgeService.deleteRule(index, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateLodge/{id}")
    @PreAuthorize("hasRole('LODGEOWNER')")
    public ResponseEntity<Lodge> updateLodge(@RequestBody UpdateLodgeDTO lodge, @PathVariable Integer id){
        lodgeService.updateLodge(lodge, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/letters")
    public List<String> getFirstLetters() {
        return lodgeService.getFirstLetters();
    }

    private Location addLocation(){
        Location location = new Location();
        location.setAddress("");
        location.setCity("");
        location.setCountry("");

        return locationService.save(location);
    }

    @GetMapping("/lodge")
    @PreAuthorize("hasRole('CLIENT')")
    public LodgeInfoDTO getById(@RequestParam Integer id) {
        return lodgeService.getById(id);
    }

    @GetMapping("/lodgeNames/{id}")
    @PreAuthorize("hasRole('LODGEOWNER')")
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

    @GetMapping("/sort")
    public List<LodgeInfoDTO> sortLodges(@RequestParam String type) {
        return lodgeService.sortLodges(type);
    }
}
