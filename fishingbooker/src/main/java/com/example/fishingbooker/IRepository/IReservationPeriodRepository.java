package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Reservation;
import com.example.fishingbooker.Model.ReservationPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IReservationPeriodRepository extends JpaRepository<ReservationPeriod, Integer> {

    @Query("select r from ReservationPeriod r where r.reservationEntity.id=?1")
    List<ReservationPeriod> findAllPeriods(Integer entityId);

}
