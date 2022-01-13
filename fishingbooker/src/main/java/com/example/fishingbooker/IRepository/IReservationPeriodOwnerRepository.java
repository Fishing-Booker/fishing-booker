package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.ReservationPeriodOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface IReservationPeriodOwnerRepository extends JpaRepository<ReservationPeriodOwner, Integer> {
    @Query("select p from ReservationPeriodOwner p where p.owner.id=?1")
    List<ReservationPeriodOwner> findAllOwnerPeriods(Integer ownerId);

    //delete from DeleteAccountRequest req where req.id=?1
    @Query("delete from ReservationPeriodOwner p where p.id=?1")
    @Transactional
    @Modifying
    void deletePeriod(Integer periodId);

    @Query("SELECT p FROM ReservationPeriodOwner p WHERE p.owner.id=?1 AND (?2 BETWEEN p.startDate AND p.endDate) AND (?3 BETWEEN p.startDate AND p.endDate)")
    List<ReservationPeriodOwner> findPeriodsByDate(Integer ownerId, Date startDate, Date endDate);
}
