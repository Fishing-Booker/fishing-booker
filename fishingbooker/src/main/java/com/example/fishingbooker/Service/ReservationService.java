package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.ReservationEntityDTO;
import com.example.fishingbooker.DTO.reservation.AddReservationDTO;
import com.example.fishingbooker.DTO.reservation.ReservationDTO;
import com.example.fishingbooker.Enum.ReservationType;
import com.example.fishingbooker.IRepository.IReservationEntityRepository;
import com.example.fishingbooker.IRepository.IReservationRepository;
import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.IService.IReservationService;
import com.example.fishingbooker.Model.Reservation;
import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    private IReservationRepository reservationRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IReservationEntityRepository entityRepository;

    @Override
    public List<Reservation> findEntityReservations(Integer entityId) {
        return reservationRepository.findEntityReservations(entityId);
    }

    @Override
    public void save(AddReservationDTO dto) {
        Reservation reservation = new Reservation();
        reservation.setStartDate(dto.getStartDate());
        reservation.setEndDate(dto.getEndDate());
        reservation.setClient(userRepository.getById(1));
        reservation.setReservationEntity(setReservationEntity(dto.getEntityId(), dto.getOwner()));
        reservation.setReservationType(ReservationType.regularReservation);
        reservationRepository.save(reservation);
    }

    private ReservationEntity setReservationEntity(Integer entityId, Integer ownerId){
        User owner = userRepository.getById(ownerId);
        ReservationEntity entity = entityRepository.findEntityById(entityId);
        entity.setOwner(owner);
        return entity;
    }
}
