package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.ReservationAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservationActionRepository extends JpaRepository<ReservationAction, Integer> {

}
