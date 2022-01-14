package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.fishingEquipment.FishingEquipmentDTO;
import com.example.fishingbooker.IService.INavigationEquipmentService;
import com.example.fishingbooker.Model.NavigationEquipment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/navEquipment", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class NavigationEquipmentController {

    @Autowired
    private INavigationEquipmentService navigationEquipmentService;

    @GetMapping("/shipNavigationEquipment/{id}")
    public List<NavigationEquipment> findShipNavigationEquipment(@PathVariable Integer id){
        return navigationEquipmentService.findShipNavigationEquipment(id);
    }

    @PutMapping("/addNavigationEquipment/{id}")
    public ResponseEntity<String> addNavigationEquipment(@RequestBody FishingEquipmentDTO navEquipment, @PathVariable Integer id){
        navigationEquipmentService.addNavigationEquipment(navEquipment, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteNavigationEquipment/{id}/{eqId}")
    public ResponseEntity<String> deleteNavigationEquipment(@PathVariable Integer eqId, @PathVariable Integer id){
        navigationEquipmentService.deleteNavigationEquipment(eqId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
