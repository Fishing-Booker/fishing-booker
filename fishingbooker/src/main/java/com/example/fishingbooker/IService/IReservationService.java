package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.reservation.AddReservationDTO;
import com.example.fishingbooker.DTO.reservation.ReservationDTO;

import java.util.List;

public interface IReservationService {

    List<ReservationDTO> findEntityReservations(Integer entityId);

    List<ReservationDTO> findOwnerEntitiesReservations(Integer ownerId);

    void save(AddReservationDTO dto);

}
