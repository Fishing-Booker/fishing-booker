package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.SubscriberDTO;
import com.example.fishingbooker.IService.ISubscriberService;
import com.example.fishingbooker.Model.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/subscribe", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class SubscriberController {

    @Autowired
    private ISubscriberService subscriberService;

    @PostMapping()
    public ResponseEntity<Subscriber> addSubscriber(@RequestBody SubscriberDTO dto) {
        subscriberService.addSubscriber(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/isSubscribed")
    public Boolean isSubscribed(@RequestBody SubscriberDTO dto) {
        return subscriberService.isSubscribed(dto);
    }

    @DeleteMapping("/unsubscribe")
    public Boolean unsubscribe(@RequestParam Integer entityId, @RequestParam Integer userId) {
        return subscriberService.unsubscribe(entityId, userId);
    }
}
