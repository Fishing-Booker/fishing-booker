package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.ClientDTO;
import com.example.fishingbooker.DTO.reservation.*;
import com.example.fishingbooker.Model.Reservation;

import java.util.Date;
import java.util.List;

public interface IReservationService {

    List<ReservationDTO> findEntityReservations(Integer entityId);

    List<ReservationDTO> findOwnerEntitiesReservations(Integer ownerId);

    List<ReservationDTO> findFutureOwnerEntitiesReservations(Integer ownerId);

    List<ReservationDTO> findPastOwnerEntitiesReservations(Integer ownerId);

    void save(AddReservationDTO dto);

    boolean checkActiveReservations(Integer ownerId);

    String getClientUsername(String entityName, Integer ownerId);

    List<ReservationDTO> getClientReservations(Integer clientId);

    void makeReservation(ClientReservationDTO dto);

    List<ReservationDTO> getCurrentReservation(Date date, Integer clientId);

    void cancelReservation(Integer id);

    List<ClientDTO> getClientsOfActiveReservations(Integer ownerId);

    ActiveReservationDTO getEntityNameOfClientActiveReservation(Integer ownerId, String clientName);

    Reservation makeReservationOwner(OwnerReservationDTO dto);

    List<ReservationForCalendarDTO> findOwnerReservations(Integer ownerId);

    boolean hasEntityFutureReservations(Integer entityId);

    List<ReservationDTO> searchClients(String username, Integer entityId);

    List<String> getEntityNamesOfActiveReservations(Integer ownerId);

    List<ReservationReportDTO> getReservationReport();
}
