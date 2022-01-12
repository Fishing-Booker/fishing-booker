package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.reservationAction.AddReservationActionDTO;
import com.example.fishingbooker.DTO.reservationAction.ReservationActionDTO;

import java.util.List;

public interface IReservationActionService {

    void save(AddReservationActionDTO dto);

    List<ReservationActionDTO> findEntityActions(Integer entityId);

    void makeReservation(Integer actionId, Integer clientId);

    List<ReservationActionDTO> getAvailableActions(Integer id);
}
