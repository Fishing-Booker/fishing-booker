package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.RatingDTO;
import com.example.fishingbooker.DTO.RatingInfoDTO;
import com.example.fishingbooker.Model.Rating;

import java.util.List;

public interface IRatingService {
    Rating addRating(RatingDTO dto);

    List<RatingInfoDTO> findAll();

    void approveRating(RatingInfoDTO dto);

    void disapproveRating(Integer ratingId);
}
