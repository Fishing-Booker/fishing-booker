package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.SubscriberDTO;
import com.example.fishingbooker.DTO.SubscriptionDTO;
import com.example.fishingbooker.IService.ISubscriberService;
import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.Model.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/subscribe", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class SubscriberController {

    @Autowired
    private ISubscriberService subscriberService;

    @PostMapping()
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Subscriber> addSubscriber(@RequestBody SubscriberDTO dto) {
        subscriberService.addSubscriber(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/isSubscribed")
    public Boolean isSubscribed(@RequestBody SubscriberDTO dto) {
        return subscriberService.isSubscribed(dto);
    }

    @DeleteMapping("/unsubscribe")
    @PreAuthorize("hasRole('CLIENT')")
    public Boolean unsubscribe(@RequestParam Integer entityId, @RequestParam Integer userId) {
        return subscriberService.unsubscribe(entityId, userId);
    }

    @GetMapping("/subscriptions")
    @PreAuthorize("hasRole('CLIENT')")
    public List<SubscriptionDTO> getSubscriptions(@RequestParam Integer id) {
        return subscriberService.getSubscriptions(id);
    }
}
