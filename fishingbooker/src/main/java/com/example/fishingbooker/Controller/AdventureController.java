package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.AdventureDTO;
import com.example.fishingbooker.DTO.EditAdventureDTO;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import com.example.fishingbooker.DTO.adventure.AdventureInfoDTO;

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
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public List<Adventure> getInstructorAdventures(@PathVariable Integer id) {
        return adventureService.findInstructorAdventures(id);
    }

    @PostMapping("/addAdventure")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Adventure> addAdventure(@RequestBody AdventureDTO adventureDTO) {
        User user = userService.findUserById(adventureDTO.getOwner());
        Location location = addLocation(adventureDTO.getAddress(), adventureDTO.getCity(), adventureDTO.getCountry());

        Integer id = 0;
        if(entityService.findEntities().size() == 0) {
            id = 1;
        } else {
            id = entityService.setId();
        }

        Adventure adventure = new Adventure(id, user, adventureDTO.getName(), location, adventureDTO.getDescription(), "",
                adventureDTO.getCancelConditions(), 0.0, adventureDTO.getBiography(), adventureDTO.getMaxPersons(),
                new ArrayList<>(), adventureDTO.getFishingEquipment());

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
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Adventure> deleteAdventure(@PathVariable Integer id) {
        adventureService.deleteAdventure(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    List<AdventureInfoDTO> getAll() {
        return adventureService.getAll();
    }

    @GetMapping("/letters")
    List<String> getFirstLetters() {
        return adventureService.getFirstLetters();
    }

    @GetMapping("/search")
    public List<AdventureInfoDTO> getSearchResults(@RequestParam(required = false) String name, @RequestParam(required = false) String letter) {
        return adventureService.searchAndFilter(name, letter);
    }

    @GetMapping("/adventure/{id}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public Adventure getAdventureById(@PathVariable Integer id) {
        return adventureService.findById(id);
    }

    @PutMapping("/editAdventure")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Adventure> editAdventure(@RequestBody EditAdventureDTO editAdventureDTO) {
        adventureService.editAdventure(editAdventureDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/adventure")
    public AdventureInfoDTO getById(@RequestParam Integer id) {
        return adventureService.getById(id);
    }

}
