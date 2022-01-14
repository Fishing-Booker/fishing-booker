package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Reservation;
import com.example.fishingbooker.Model.ReservationAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IReservationActionRepository extends JpaRepository<ReservationAction, Integer> {

    @Query("SELECT a FROM ReservationAction a WHERE a.reservationEntity.id=?1")
    List<ReservationAction> findEntityActions(Integer entityId);

    @Query("UPDATE ReservationAction a SET a.isBooked=true, a.client.id=?2 WHERE a.id=?1")
    @Modifying
    @Transactional
    void makeReservation(Integer actionId, Integer clientId);

    @Query("delete from ReservationAction a where a.id=?1")
    @Modifying
    @Transactional
    void deleteById(Integer id);
}
