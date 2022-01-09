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

import java.util.ArrayList;
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
    public List<ReservationDTO> findEntityReservations(Integer entityId) {
        List<ReservationDTO> reservations = new ArrayList<>();
        for (Reservation r : reservationRepository.findEntityReservations(entityId)) {
            reservations.add(new ReservationDTO(r.getStartDate(), r.getEndDate(), r.getClient().getUsername(), entityId,
                    r.getReservationEntity().getName()));
        }
        return reservations;
    }

    @Override
    public List<ReservationDTO> findOwnerEntitiesReservations(Integer ownerId) {
        List<ReservationDTO> reservations = new ArrayList<>();
        for (ReservationEntity entity : entityRepository.findOwnerEntities(ownerId)) {
            List<ReservationDTO> entityReservations = findEntityReservations(entity.getId());
            reservations.addAll(entityReservations);
        }
        return reservations;
    }

    @Override
    public void save(AddReservationDTO dto) {
        Reservation reservation = new Reservation();
        reservation.setStartDate(dto.getStartDate());
        reservation.setEndDate(dto.getEndDate());
        reservation.setClient(userRepository.findByUsername("sara"));
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
