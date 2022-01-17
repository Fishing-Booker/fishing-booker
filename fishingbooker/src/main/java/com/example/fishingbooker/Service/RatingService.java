package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.RatingDTO;
import com.example.fishingbooker.DTO.RatingInfoDTO;
import com.example.fishingbooker.IRepository.IRatingRepository;
import com.example.fishingbooker.IService.IRatingService;
import com.example.fishingbooker.IService.IReservationEntityService;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Mapper.RatingMapper;
import com.example.fishingbooker.Model.Rating;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RatingService implements IRatingService {

    @Autowired
    IRatingRepository ratingRepository;

    @Autowired
    IReservationEntityService reservationEntityService;

    @Autowired
    IUserService userService;

    @Override
    public Rating addRating(RatingDTO dto) {
        Rating rating = RatingMapper.mapDTOToModel(dto);
        rating.setReservationEntity(reservationEntityService.getEntityById(dto.getEntityId()));
        rating.setUser(userService.findUserById(dto.getClientId()));
        Rating r = ratingRepository.save(rating);
        reservationEntityService.updateEntityAverageGrade(dto.getEntityId(), calculateGrade(dto.getEntityId()));
        return r;
    }

    private double calculateGrade(Integer entityId){
        List<Rating> ratings = ratingRepository.findAll();
        double sum = 0;
        for (Rating r : ratings) {
            if(r.getReservationEntity().getId() == entityId) {
                sum += r.getGrade();
            }
        }
        return sum/ratings.size();
    }

    @Override
    public List<RatingInfoDTO> findAll() {
        List<Rating> allRatings = ratingRepository.findAll();
        List<RatingInfoDTO> dtos = new ArrayList<>();
        for (Rating r : allRatings) {
            if(!r.isApproved() && !r.isDispproved()) {
                RatingInfoDTO dto = RatingMapper.mapModelToDTOInfo(r);
                dtos.add(dto);
            }
        }
        return dtos;
    }

    @Override
    public void approveRating(RatingInfoDTO dto) {
        ratingRepository.approveRating(dto.getId());
        Rating r = ratingRepository.getById(dto.getId());
        userService.sendEmailApprovedComment(r.getUser(), dto);
    }

    @Override
    public void disapproveRating(Integer ratingId) {
        ratingRepository.disapproveRating(ratingId);
    }
}
