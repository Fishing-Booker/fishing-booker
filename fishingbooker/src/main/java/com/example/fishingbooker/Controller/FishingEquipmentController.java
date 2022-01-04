package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.EquipmentDTO;
import com.example.fishingbooker.IService.IFishingEquipmentService;
import com.example.fishingbooker.Model.FishingEquipment;
import com.example.fishingbooker.Model.NavigationEquipment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/fishEquipment", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class FishingEquipmentController {

    @Autowired
    private IFishingEquipmentService fishingEquipmentService;

    @GetMapping("/shipFishingEquipment/{id}")
    public List<FishingEquipment> findShipNavigationEquipment(@PathVariable Integer id){
        return fishingEquipmentService.findShipFishingEquipment(id);
    }

    @PutMapping("/addFishingEquipment/{id}")
    public ResponseEntity<String> addFishingEquipment(@RequestBody EquipmentDTO fishEquipment, @PathVariable Integer id){
        fishingEquipmentService.addFishingEquipment(fishEquipment, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteFishingEquipment/{id}/{eqId}")
    public ResponseEntity<String> deleteFishingEquipment(@PathVariable Integer eqId, @PathVariable Integer id){
        fishingEquipmentService.deleteFishingEquipment(eqId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
