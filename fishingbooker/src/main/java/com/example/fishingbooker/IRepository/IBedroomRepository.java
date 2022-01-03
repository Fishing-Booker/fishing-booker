package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Bedroom;
import com.example.fishingbooker.Model.Lodge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IBedroomRepository extends JpaRepository<Bedroom, Integer> {

    @Query("SELECT b FROM Bedroom b WHERE b.lodge.id=?1")
    List<Bedroom> findLodgeBedrooms(Integer lodgeId);

    @Query("update Bedroom b " + "set b.roomNumber=?1 " + "WHERE b.lodge.id=?2 and b.bedroomType=0")
    @Modifying
    @Transactional
    void updateOneBedRoom(Integer roomNumber, Integer lodgeId);

    @Query("update Bedroom b " + "set b.roomNumber=?1 " + "WHERE b.lodge.id=?2 and b.bedroomType=1")
    @Modifying
    @Transactional
    void updateTwoBedRoom(Integer roomNumber, Integer lodgeId);

    @Query("update Bedroom b " + "set b.roomNumber=?1 " + "WHERE b.lodge.id=?2 and b.bedroomType=2")
    @Modifying
    @Transactional
    void updateThreeBedRoom(Integer roomNumber, Integer lodgeId);

    @Query("update Bedroom b " + "set b.roomNumber=?1 " + "WHERE b.lodge.id=?2 and b.bedroomType=3")
    @Modifying
    @Transactional
    void updateFourBedRoom(Integer roomNumber, Integer lodgeId);

}
