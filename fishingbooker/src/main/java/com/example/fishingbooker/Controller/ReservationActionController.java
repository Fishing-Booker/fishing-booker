package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.ReservationActionDTO;
import com.example.fishingbooker.IService.IReservationActionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/actions", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class ReservationActionController {

    @Autowired
    private IReservationActionService actionService;

    @PostMapping("/addAction")
    public ResponseEntity<String> addAction(@RequestBody ReservationActionDTO action){
        actionService.save(action);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
