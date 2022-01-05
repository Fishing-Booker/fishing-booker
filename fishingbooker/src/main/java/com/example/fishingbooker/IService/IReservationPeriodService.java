package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.reservationPeriod.ReservationPeriodDTO;
import com.example.fishingbooker.Model.ReservationPeriod;

import java.util.List;

public interface IReservationPeriodService {

    void save(ReservationPeriodDTO dto);

    List<ReservationPeriod> findAllPeriods(Integer entityId);

    List<ReservationPeriod> findFreePeriods(Integer entityId);

}
