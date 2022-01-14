package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.RatingDTO;
import com.example.fishingbooker.IService.IRatingService;
import com.example.fishingbooker.Model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rating", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class RatingController {

    @Autowired
    IRatingService ratingService;

    @PostMapping("/add")
    public ResponseEntity<Rating> addRating(@RequestBody RatingDTO dto) {
        ratingService.addRating(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
