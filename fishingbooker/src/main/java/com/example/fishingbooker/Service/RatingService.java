package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.RatingDTO;
import com.example.fishingbooker.IRepository.IRatingRepository;
import com.example.fishingbooker.IService.IRatingService;
import com.example.fishingbooker.IService.IReservationEntityService;
import com.example.fishingbooker.Mapper.RatingMapper;
import com.example.fishingbooker.Model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService implements IRatingService {

    @Autowired
    IRatingRepository ratingRepository;

    @Autowired
    IReservationEntityService reservationEntityService;

    @Override
    public Rating addRating(RatingDTO dto) {
        Rating rating = RatingMapper.mapDTOToModel(dto);
        rating.setReservationEntity(reservationEntityService.getEntityById(dto.getEntityId()));
        return ratingRepository.save(rating);
    }
}
