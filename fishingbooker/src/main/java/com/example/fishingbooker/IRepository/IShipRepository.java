package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Lodge;
import com.example.fishingbooker.Model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IShipRepository extends JpaRepository<Ship, Integer> {

    @Query("SELECT s FROM Ship s WHERE s.owner.id=?1 and s.isDeleted=false")
    List<Ship> findOwnerShips(Integer ownerId);

}
