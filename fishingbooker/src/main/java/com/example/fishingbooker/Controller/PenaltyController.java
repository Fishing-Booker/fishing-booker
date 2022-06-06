package com.example.fishingbooker.Controller;

import com.example.fishingbooker.IService.IPenaltyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/penalties", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class PenaltyController {

    @Autowired
    private IPenaltyService penaltyService;

    @GetMapping()
    @PreAuthorize("hasRole('CLIENT')")
    public Integer getClientPenalties(@RequestParam Integer clientId) {
        return penaltyService.findClientPenalties(clientId);
    }

    @PutMapping()
    public ResponseEntity<String> annulPenalties(@RequestBody Integer clientId) {
        penaltyService.annulPenalties(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
