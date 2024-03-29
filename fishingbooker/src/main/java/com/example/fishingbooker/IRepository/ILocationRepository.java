package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Location;
import com.example.fishingbooker.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ILocationRepository extends JpaRepository<Location, Integer> {

    @Query("SELECT l FROM Location l WHERE l.address = ?1 and l.city = ?2 and l.country = ?3")
    Location findByAddress(String address, String city, String country);

    @Query("update Location l " + "set l.address=?1, l.city=?2, l.country=?3 " + "WHERE l.id=?4")
    @Modifying
    @Transactional
    void updateLodge(String address, String city, String country, Integer locationId);

    @Query("SELECT l FROM Location l WHERE l.id = ?1")
    Location findLocationById(Integer locationId);

    @Query("update Location l " +
            "set l.address=?1, l.city=?2, l.country=?3, l.longitude=?4, l.latitude=?5 " +
            "WHERE l.id=?6")
    @Modifying
    @Transactional
    void changeLocation(String address, String city, String country, double longitude, double latitude, Integer locationId);

}
