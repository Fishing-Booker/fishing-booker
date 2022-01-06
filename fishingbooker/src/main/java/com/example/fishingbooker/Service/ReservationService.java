package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.reservation.ReservationDTO;
import com.example.fishingbooker.Enum.ReservationType;
import com.example.fishingbooker.IRepository.IReservationRepository;
import com.example.fishingbooker.IService.IReservationService;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Model.Reservation;
import com.example.fishingbooker.Model.ReservationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    private IReservationRepository reservationRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private ReservationEntityService entityService;

    @Override
    public List<Reservation> findEntityReservations(Integer entityId) {
        return reservationRepository.findEntityReservations(entityId);
    }

    @Override
    public void save(ReservationDTO dto) {
        Reservation reservation = new Reservation();
        reservation.setStartDate(dto.getStartDate());
        reservation.setEndDate(dto.getEndDate());
        reservation.setClient(userService.findUserById(1));
        ReservationEntity entity = entityService.findEntityById(1);
        reservation.setReservationEntity(entity);
        reservation.setReservationType(ReservationType.regularReservation);
        reservationRepository.save(reservation);
    }
}
