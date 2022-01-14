package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.reservationAction.AddReservationActionDTO;
import com.example.fishingbooker.DTO.reservationAction.ReservationActionDTO;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IReservationActionService {

    void save(AddReservationActionDTO dto);

    List<ReservationActionDTO> findEntityActions(Integer entityId);

    void makeReservation(Integer actionId, Integer clientId) throws MessagingException, UnsupportedEncodingException;

    List<ReservationActionDTO> getAvailableActions(Integer id);

    void deleteAction(Integer id);
}
