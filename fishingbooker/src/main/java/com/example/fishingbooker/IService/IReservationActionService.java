package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.reservationAction.AddReservationActionDTO;
import com.example.fishingbooker.DTO.reservationAction.ReservationActionDTO;
import com.example.fishingbooker.Model.Reservation;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IReservationActionService {

    Reservation save(AddReservationActionDTO dto);

    void addShipOwnerAction(Reservation reservation, Integer ownerId);

    List<ReservationActionDTO> findEntityActions(Integer entityId);

    void makeReservation(Integer actionId, Integer clientId) throws MessagingException, UnsupportedEncodingException;

    List<ReservationActionDTO> getAvailableActions(Integer id);

    void deleteAction(Integer id);
}
