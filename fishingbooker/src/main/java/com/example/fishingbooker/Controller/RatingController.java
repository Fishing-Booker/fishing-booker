package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.RatingDTO;
import com.example.fishingbooker.DTO.RatingInfoDTO;
import com.example.fishingbooker.IService.IRatingService;
import com.example.fishingbooker.Model.Rating;
import com.example.fishingbooker.Model.ReservationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rating", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class RatingController {

    @Autowired
    IRatingService ratingService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Rating> addRating(@RequestBody RatingDTO dto) {
        ratingService.addRating(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public List<RatingInfoDTO> getRatings()
    {
        return ratingService.findAll();
    }

    @PutMapping("/approve")
    public ResponseEntity<Rating> approveComment(@RequestBody RatingInfoDTO dto){
        ratingService.approveRating(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/disapprove/{id}")
    public ResponseEntity<Rating> disapproveComment(@PathVariable Integer id){
        ratingService.disapproveRating(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
