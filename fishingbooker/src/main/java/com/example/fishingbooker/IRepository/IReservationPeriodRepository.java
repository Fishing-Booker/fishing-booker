package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Reservation;
import com.example.fishingbooker.Model.ReservationPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface IReservationPeriodRepository extends JpaRepository<ReservationPeriod, Integer> {

    @Query("SELECT p FROM ReservationPeriod p WHERE p.reservationEntity.id=?1")
    List<ReservationPeriod> findAllPeriods(Integer entityId);

    @Query("SELECT p FROM ReservationPeriod p WHERE p.reservationEntity.id=?1 AND (?2 BETWEEN p.startDate AND p.endDate) AND (?3 BETWEEN p.startDate AND p.endDate)")
    List<ReservationPeriod> findPeriodsByDate(Integer entityId, Date startDate, Date endDate);

    @Query("delete from ReservationPeriod p where p.id=?1")
    @Transactional
    @Modifying
    void deletePeriod(Integer periodId);

}
