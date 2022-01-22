package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.fishingEquipment.AddFishingEquipmentDTO;
import com.example.fishingbooker.DTO.fishingEquipment.FishingEquipmentDTO;
import com.example.fishingbooker.IService.IFishingEquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/fishEquipment", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class FishingEquipmentController {

    @Autowired
    IFishingEquipmentService fishingEquipmentService;

    @GetMapping("/shipFishingEquipment/{id}")
    public List<FishingEquipmentDTO> findShipFishingEquipment(@PathVariable Integer id){
        return fishingEquipmentService.findShipFishingEquipment(id);
    }

    @PutMapping("/addFishingEquipment")
    @PreAuthorize("hasRole('SHIPOWNER')")
    public ResponseEntity<String> addFishingEquipment(@RequestBody AddFishingEquipmentDTO fishEquipment){
        fishingEquipmentService.addFishingEquipment(fishEquipment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteFishingEquipment/{id}/{eqId}")
    @PreAuthorize("hasRole('SHIPOWNER')")
    public ResponseEntity<String> deleteFishingEquipment(@PathVariable Integer eqId, @PathVariable Integer id){
        fishingEquipmentService.deleteFishingEquipment(eqId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
