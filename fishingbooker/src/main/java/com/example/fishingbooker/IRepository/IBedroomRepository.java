package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Bedroom;
import com.example.fishingbooker.Model.Lodge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBedroomRepository extends JpaRepository<Bedroom, Integer> {
    @Query("SELECT b FROM Bedroom b WHERE b.lodge.id=?1")
    List<Bedroom> findLodgeBedrooms(Integer lodgeId);

}
