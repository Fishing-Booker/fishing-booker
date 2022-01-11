package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT r FROM Reservation r WHERE r.reservationEntity.id=?1")
    List<Reservation> findEntityReservations(Integer entityId);

    @Query("SELECT r FROM Reservation r WHERE r.id=?1")
    Reservation findReservationById(Integer id);

}
