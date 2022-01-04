package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.NavigationEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface INavigationEquipmentRepository extends JpaRepository<NavigationEquipment, Integer> {

    @Query("SELECT e FROM NavigationEquipment e WHERE e.ship.id=?1 and e.isDeleted=false")
    List<NavigationEquipment> findShipNavigationEquipment(Integer shipId);

    @Query("update NavigationEquipment e set e.isDeleted=true WHERE e.id=?1 and e.ship.id=?2")
    @Modifying
    @Transactional
    void deleteNavigationEquipment(Integer equipmentId, Integer shipId);

}
