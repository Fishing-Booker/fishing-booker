package com.example.fishingbooker.Mapper;

import com.example.fishingbooker.DTO.reservation.ClientReservationDTO;
import com.example.fishingbooker.DTO.reservation.OwnerReservationDTO;
import com.example.fishingbooker.DTO.reservation.ReservationDTO;
import com.example.fishingbooker.Enum.ReservationType;
import com.example.fishingbooker.Model.Reservation;

public class ReservationMapper {

    public static ReservationDTO mapToDTO(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setReservationId(reservation.getId());
        dto.setStartDate(reservation.getStartDate());
        dto.setEndDate(reservation.getEndDate());
        dto.setEntityName(reservation.getReservationEntity().getName());
        dto.setEntityId(reservation.getReservationEntity().getId());
        dto.setPrice(reservation.getPrice());
        dto.setAdditionalService(reservation.getAdditionalServices());
        dto.setRegularService(reservation.getRegularService());
        return dto;
    }

    public static Reservation mapDTOToModel(ClientReservationDTO dto) {
        Reservation reservation = new Reservation();
        reservation.setReservationType(ReservationType.regularReservation);
        reservation.setStartDate(dto.getStartDate());
        reservation.setEndDate(dto.getEndDate());
        reservation.setBooked(true);
        reservation.setPrice(dto.getPrice());
        reservation.setAdditionalServices(dto.getAdditionalServices());
        reservation.setRegularService(dto.getRegularService());
        return reservation;
    }

    public static Reservation ownerMapDTOToModel(OwnerReservationDTO dto) {
        Reservation reservation = new Reservation();
        reservation.setReservationType(ReservationType.regularReservation);
        reservation.setStartDate(dto.getStartDate());
        reservation.setEndDate(dto.getEndDate());
        reservation.setMaxPersons(dto.getNumberOfGuests());
        reservation.setPrice(dto.getPrice());
        reservation.setAdditionalServices(dto.getAdditionalServices());
        reservation.setRegularService(dto.getRegularService());
        return reservation;
    }
}
