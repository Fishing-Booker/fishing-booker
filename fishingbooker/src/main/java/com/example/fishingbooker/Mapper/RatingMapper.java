package com.example.fishingbooker.Mapper;

import com.example.fishingbooker.DTO.RatingDTO;
import com.example.fishingbooker.Model.Rating;

public class RatingMapper {

    public static Rating mapDTOToModel(RatingDTO dto) {
        Rating rating = new Rating();
        rating.setComment(dto.getComment());
        rating.setGrade(dto.getGrade());
        rating.setApproved(RatingDTO.isIsApproved());
        return rating;
    }
}
