package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.LodgeDTO;
import com.example.fishingbooker.DTO.lodge.LodgeInfoDTO;
import com.example.fishingbooker.Enum.BedroomType;
import com.example.fishingbooker.IService.*;
import com.example.fishingbooker.Model.*;
import com.example.fishingbooker.Service.LodgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lodges")
@CrossOrigin
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

    @PostMapping("/addLodge")
    public ResponseEntity<Lodge> addLodge(@RequestBody LodgeDTO lodgeDTO){

        ReservationEntity entity = new ReservationEntity();

        User owner = userService.findUserById(lodgeDTO.getOwner());
        entity.setOwner(owner);

        entity.setName(lodgeDTO.getName());

        Location location = addLocation(lodgeDTO.getAddress(), lodgeDTO.getCity(), lodgeDTO.getCountry());
        entity.setLocation(location);

        entity.setDescription(lodgeDTO.getDescription());
        entity.setRules("");
        entity.setCancelConditions("");
        entity.setDeleted(false);
        entity.setAverageGrade(0.0);
        entity.setImages(new ArrayList<>());

        Integer id = 0;
        if(entityService.findEntities().size() == 0){
            id = 1;
        } else {
            id = entityService.findEntities().get(entityService.findEntities().size() - 1).getId() + 1;
        }

        Lodge lodge = new Lodge(id, entity.getOwner(), entity.getName(), entity.getLocation(),
                entity.getDescription(), entity.getRules(), entity.getCancelConditions(), entity.getAverageGrade(),
                entity.getImages());
        lodgeService.save(lodge);

        addBedrooms(lodge, lodgeDTO.getOneBed(), lodgeDTO.getTwoBed(), lodgeDTO.getThreeBed(), lodgeDTO.getFourBed());

        return new ResponseEntity<>(HttpStatus.CREATED);//201
    }

    @GetMapping()
    public List<LodgeInfoDTO> getAll() {
        return lodgeService.getAll();
    }

    private Location addLocation(String address, String city, String country){
        Location location = new Location();
        location.setAddress(address);
        location.setCity(city);
        location.setCountry(country);

        return location = locationService.save(location);
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
