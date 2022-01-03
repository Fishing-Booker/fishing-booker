package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Lodge;
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
    void deleteLodge(Integer lodgeId);

}
