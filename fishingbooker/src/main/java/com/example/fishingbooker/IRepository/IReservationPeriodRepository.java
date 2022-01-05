package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.ReservationPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservationPeriodRepository extends JpaRepository<ReservationPeriod, Integer> {
}
