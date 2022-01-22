package com.example.fishingbooker.Mapper;

import com.example.fishingbooker.DTO.reservation.*;
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
        reservation.setMaxPersons(dto.getNumberOfGuests());
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

    public static ReservationForCalendarDTO mapModelToCalendarDTO(Reservation reservation) {
        ReservationForCalendarDTO dto = new ReservationForCalendarDTO();
        dto.setReservationId(reservation.getId());
        dto.setClientName(reservation.getClient().getName());
        dto.setClientSurname(reservation.getClient().getSurname());
        dto.setStartDate(reservation.getStartDate());
        dto.setEndDate(reservation.getEndDate());
        dto.setPrice(reservation.getPrice());
        dto.setEntityName(reservation.getReservationEntity().getName());
        dto.setReservationType(reservation.getReservationType());
        dto.setRegularService(reservation.getRegularService());
        return dto;
    }

    public static ReservationReportDTO mapModelToReportDTO(Reservation reservation) {
        ReservationReportDTO dto = new ReservationReportDTO();
        dto.setId(reservation.getId());
        dto.setEntityName(reservation.getReservationEntity().getName());
        dto.setUserName(reservation.getClient().getName());
        dto.setUserSurname(reservation.getClient().getSurname());
        dto.setPrice(reservation.getPrice());
        return dto;
    }
}
