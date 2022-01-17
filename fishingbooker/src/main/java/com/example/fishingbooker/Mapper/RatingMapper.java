package com.example.fishingbooker.Mapper;

import com.example.fishingbooker.DTO.RatingDTO;
import com.example.fishingbooker.DTO.RatingInfoDTO;
import com.example.fishingbooker.Model.Rating;

public class RatingMapper {

    public static Rating mapDTOToModel(RatingDTO dto) {
        Rating rating = new Rating();
        rating.setComment(dto.getComment());
        rating.setGrade(dto.getGrade());
        rating.setApproved(RatingDTO.isIsApproved());
        return rating;
    }

    public static RatingDTO mapModelToDTO(Rating rating){
        RatingDTO dto = new RatingDTO();
        dto.setId(rating.getId());
        dto.setComment(rating.getComment());
        dto.setGrade(rating.getGrade());
        dto.setEntityId(rating.getReservationEntity().getId());
        dto.setClientId(rating.getUser().getId());
        return dto;
    }

    public static RatingInfoDTO mapModelToDTOInfo(Rating rating){
        RatingInfoDTO dto = new RatingInfoDTO();
        dto.setId(rating.getId());
        dto.setComment(rating.getComment());
        dto.setGrade(rating.getGrade());
        dto.setEntityName(rating.getReservationEntity().getName());
        dto.setClientName(rating.getUser().getName() + " " + rating.getUser().getSurname());
        return dto;
    }
}
