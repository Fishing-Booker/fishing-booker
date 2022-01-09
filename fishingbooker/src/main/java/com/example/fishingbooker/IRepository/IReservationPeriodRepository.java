package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Reservation;
import com.example.fishingbooker.Model.ReservationPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface IReservationPeriodRepository extends JpaRepository<ReservationPeriod, Integer> {

    @Query("SELECT p FROM ReservationPeriod p WHERE p.reservationEntity.id=?1")
    List<ReservationPeriod> findAllPeriods(Integer entityId);

    @Query("SELECT p FROM ReservationPeriod p WHERE p.reservationEntity.id=?1 OR p.startDate=?2 OR p.endDate=?3")
    List<ReservationPeriod> findPeriodsByDate(Integer entityId, Date startDate, Date endDate);

}
