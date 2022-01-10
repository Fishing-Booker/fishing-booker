package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Reservation;
import com.example.fishingbooker.Model.ReservationAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IReservationActionRepository extends JpaRepository<ReservationAction, Integer> {

    @Query("SELECT a FROM ReservationAction a WHERE a.reservationEntity.id=?1")
    List<ReservationAction> findEntityActions(Integer entityId);

}
