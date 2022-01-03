package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IShipRepository extends JpaRepository<Ship, Integer> {

    @Query("SELECT s FROM Ship s WHERE s.owner.id=?1 and s.isDeleted=false")
    List<Ship> findOwnerShips(Integer ownerId);

    @Query("update Ship s set s.isDeleted=true WHERE s.id=?1")
    @Modifying
    @Transactional
    void deleteShip(Integer shipId);

    @Query("SELECT s FROM Ship s WHERE s.id=?1")
    Ship findShipById(Integer id);

    @Query("update Ship s " +
            "set s.name=?1, s.description=?2, s.shipType=?3, s.length=?4, s.engineNumber=?5, s.enginePower=?6, s.maxSpeed=?7, s.capacity=?8 " +
            "WHERE s.id=?9")
    @Modifying
    @Transactional
    void updateShip(String name, String description, String shipType, double length, Integer engineNumber, double enginePower, double maxSpeed, Integer capacity, Integer shipId);

}
