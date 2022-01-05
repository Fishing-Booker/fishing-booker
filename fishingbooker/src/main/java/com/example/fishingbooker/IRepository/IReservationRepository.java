package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("select r from Reservation r where r.reservationPeriod.reservationEntity.id=?1")
    List<Reservation> findEntityReservations(Integer entityId);

}
