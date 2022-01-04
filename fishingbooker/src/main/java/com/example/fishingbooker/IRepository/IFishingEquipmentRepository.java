package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.FishingEquipment;
import com.example.fishingbooker.Model.NavigationEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IFishingEquipmentRepository extends JpaRepository<FishingEquipment, Integer> {

    @Query("SELECT e FROM FishingEquipment e WHERE e.reservationEntity.id=?1")
    List<FishingEquipment> findShipFishingEquipment(Integer shipId);

    @Query("DELETE FROM FishingEquipment e WHERE e.id=?1 and e.reservationEntity.id=?2")
    @Modifying
    @Transactional
    void deleteFishingEquipment(Integer equipmentId, Integer shipId);
}
