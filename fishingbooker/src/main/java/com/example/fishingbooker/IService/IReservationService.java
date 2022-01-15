package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.ClientDTO;
import com.example.fishingbooker.DTO.reservation.ActiveReservationDTO;
import com.example.fishingbooker.DTO.reservation.AddReservationDTO;
import com.example.fishingbooker.DTO.reservation.ClientReservationDTO;
import com.example.fishingbooker.DTO.reservation.ReservationDTO;

import java.util.Date;
import java.util.List;

public interface IReservationService {

    List<ReservationDTO> findEntityReservations(Integer entityId);

    List<ReservationDTO> findOwnerEntitiesReservations(Integer ownerId);

    void save(AddReservationDTO dto);

    boolean checkActiveReservations(Integer ownerId);

    String getClientUsername(String entityName, Integer ownerId);

    List<ReservationDTO> getClientReservations(Integer clientId);

    void makeReservation(ClientReservationDTO dto);

    List<ReservationDTO> getCurrentReservation(Date date, Integer clientId);

    void cancelReservation(Integer id);

    List<ClientDTO> getClientsOfActiveReservations(Integer ownerId);

    ActiveReservationDTO getEntityNameOfClientActiveReservation(Integer ownerId, String clientName);
}
