package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Rating;
import com.example.fishingbooker.Service.RatingService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IRatingRepository extends JpaRepository<Rating, Integer> {
    @Query("update Rating r set r.isApproved=true where r.id=?1")
    @Modifying
    @Transactional
    Rating approveRating(Integer ratingId);

    @Query("update Rating r set r.isDisapproved=true where r.id=?1")
    @Modifying
    @Transactional
    Rating disapproveRating(Integer ratingId);

    @Query("select r from Rating r where r.id=?1")
    Rating getById(Integer id);
}
