package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.CanceledReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface ICanceledReservationRepository extends JpaRepository<CanceledReservation, Integer> {

    @Query("SELECT c FROM CanceledReservation c WHERE c.client.id=?1 AND c.entity.id=?2 AND c.startDate=?3 AND c.endDate=?4")
    CanceledReservation findCanceledReservation(Integer clientId, Integer entityId, Date startDate, Date endDate);
}
