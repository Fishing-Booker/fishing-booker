package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.ShipOwnerReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IShipOwnerReservationRepository extends JpaRepository<ShipOwnerReservation, Integer> {

    @Query("SELECT r FROM ShipOwnerReservation r WHERE r.owner.id=?1")
    List<ShipOwnerReservation> findOwnerReservations(Integer ownerId);

}
