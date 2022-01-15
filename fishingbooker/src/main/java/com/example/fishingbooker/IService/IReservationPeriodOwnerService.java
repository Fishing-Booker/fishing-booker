package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.reservationPeriod.ReservationPeriodDTO;
import com.example.fishingbooker.DTO.reservationPeriodOwner.ReservationPeriodOwnerDTO;

import java.util.Date;
import java.util.List;

public interface IReservationPeriodOwnerService {
    void save(ReservationPeriodOwnerDTO dto);

    List<ReservationPeriodOwnerDTO> findAllPeriods(Integer ownerId);

    List<ReservationPeriodDTO> getAvailablePeriods(Integer ownerId, Date startDate, Date endDate);

    List<ReservationPeriodOwnerDTO> getShipOwnerFreePeriods(Integer ownerId);
}
