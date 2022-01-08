package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.reservationPeriod.ReservationPeriodDTO;
import com.example.fishingbooker.Model.ReservationPeriod;

import java.util.List;

public interface IReservationPeriodService {

    void save(ReservationPeriodDTO dto);

    List<ReservationPeriodDTO> findAllPeriods(Integer entityId);

    List<ReservationPeriodDTO> findFreePeriods(Integer entityId);

}
