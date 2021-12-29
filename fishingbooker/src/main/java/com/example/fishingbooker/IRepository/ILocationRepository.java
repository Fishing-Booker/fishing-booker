package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Location;
import com.example.fishingbooker.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ILocationRepository extends JpaRepository<Location, Integer> {

    @Query("SELECT l FROM Location l WHERE l.address = ?1 and l.city = ?2 and l.country = ?3")
    Location findByAddress(String address, String city, String country);

}
