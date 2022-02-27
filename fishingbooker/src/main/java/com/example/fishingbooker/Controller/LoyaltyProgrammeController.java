package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.loyaltyProgramme.LoyaltyProgrammeDTO;
import com.example.fishingbooker.IService.ILoyaltyProgrammeService;
import com.example.fishingbooker.Model.LoyaltyProgramme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/loyaltyProgramme", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class LoyaltyProgrammeController {
    @Autowired
    private ILoyaltyProgrammeService loyaltyProgrammeService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN') || hasRole('DEFADMIN')")
    public ResponseEntity<LoyaltyProgramme> addLoyaltyProgramme(@RequestBody LoyaltyProgrammeDTO loyaltyProgramme){
        loyaltyProgrammeService.add(loyaltyProgramme);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('ADMIN') || hasRole('DEFADMIN')")
    public LoyaltyProgramme getLoyaltyProgramme(){
        return loyaltyProgrammeService.get();
    }

}
