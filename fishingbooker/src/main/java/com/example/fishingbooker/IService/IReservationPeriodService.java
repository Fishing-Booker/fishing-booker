package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.reservationPeriod.AddReservationPeriodDTO;
import com.example.fishingbooker.DTO.reservationPeriod.ReservationPeriodDTO;

import java.util.Date;
import java.util.List;

public interface IReservationPeriodService {

    void save(AddReservationPeriodDTO dto);

    List<ReservationPeriodDTO> findAllPeriods(Integer entityId);

    List<ReservationPeriodDTO> findFreePeriods(Integer entityId);

    List<ReservationPeriodDTO> getAvailablePeriods(Integer entityId, Date startDate, Date endDate);

    List<ReservationPeriodDTO> findFreePeriodsForShipAndOwner(Integer entityId, Integer ownerId);
}
