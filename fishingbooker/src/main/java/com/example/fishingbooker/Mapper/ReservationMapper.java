package com.example.fishingbooker.Mapper;

import com.example.fishingbooker.DTO.reservation.ClientReservationDTO;
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
        return dto;
    }

    public static Reservation mapDTOToModel(ClientReservationDTO dto) {
        Reservation reservation = new Reservation();
        reservation.setReservationType(ReservationType.regularReservation);
        reservation.setStartDate(dto.getStartDate());
        reservation.setEndDate(dto.getEndDate());
        return reservation;
    }
}
