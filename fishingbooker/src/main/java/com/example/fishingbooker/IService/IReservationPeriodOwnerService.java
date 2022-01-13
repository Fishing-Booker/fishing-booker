package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.reservationPeriodOwner.ReservationPeriodOwnerDTO;

import java.util.List;

public interface IReservationPeriodOwnerService {
    void save(ReservationPeriodOwnerDTO dto);

    List<ReservationPeriodOwnerDTO> findAllPeriods(Integer ownerId);


}
