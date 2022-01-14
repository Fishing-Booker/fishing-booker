package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRatingRepository extends JpaRepository<Rating, Integer> {
}
