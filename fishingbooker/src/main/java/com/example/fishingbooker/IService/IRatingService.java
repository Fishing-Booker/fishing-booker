package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.RatingDTO;
import com.example.fishingbooker.Model.Rating;

public interface IRatingService {
    Rating addRating(RatingDTO dto);
}
