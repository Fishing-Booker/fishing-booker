package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.ClientDTO;
import com.example.fishingbooker.DTO.ReservationEntityDTO;
import com.example.fishingbooker.DTO.reservation.ActiveReservationDTO;
import com.example.fishingbooker.DTO.reservation.AddReservationDTO;
import com.example.fishingbooker.DTO.reservation.ClientReservationDTO;
import com.example.fishingbooker.DTO.reservation.ReservationDTO;
import com.example.fishingbooker.Enum.ReservationType;
import com.example.fishingbooker.IRepository.IReservationEntityRepository;
import com.example.fishingbooker.IRepository.IReservationRepository;
import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.IService.IEmailService;
import com.example.fishingbooker.IService.IReservationService;
import com.example.fishingbooker.Mapper.ReservationMapper;
import com.example.fishingbooker.Model.Reservation;
import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    private IReservationRepository reservationRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IReservationEntityRepository entityRepository;

    @Autowired
    private IEmailService emailService;

    @Override
    public List<ReservationDTO> findEntityReservations(Integer entityId) {
        List<ReservationDTO> reservations = new ArrayList<>();
        for (Reservation r : reservationRepository.findEntityReservations(entityId)) {
            reservations.add(new ReservationDTO(r.getId(), r.getStartDate(), r.getEndDate(), r.getClient().getUsername(), entityId,
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
        reservation.setClient(userRepository.findByUsername(dto.getClientUsername()));
        reservation.setReservationEntity(setReservationEntity(dto.getEntityName(), dto.getOwner()));
        reservation.setReservationType(ReservationType.regularReservation);
        reservationRepository.save(reservation);
    }

    @Override
    public boolean checkActiveReservations(Integer ownerId){
        List<ReservationDTO> reservations = findOwnerEntitiesReservations(ownerId);
        for (ReservationDTO reservation : reservations) {
            if(isReservationActive(reservation))
                return true;
        }
        return false;
    }

    @Override
    public List<ClientDTO> getClientsOfActiveReservations(Integer ownerId) {
        List<ClientDTO> clientDTOS = new ArrayList<>();
        List<ReservationDTO> reservations = findOwnerEntitiesReservations(ownerId);
        for (ReservationDTO reservation : reservations) {
            if(isReservationActive(reservation)) {
                User user = userRepository.findByUsername(reservation.getClientUsername());
                ClientDTO dto = new ClientDTO(user.getId(), user.getName());
                clientDTOS.add(dto);
            }
        }
        return clientDTOS;
    }

    @Override
    public ActiveReservationDTO getEntityNameOfClientActiveReservation(Integer ownerId, String clientName) {
        List<ReservationDTO> reservations = findOwnerEntitiesReservations(ownerId);
        for (ReservationDTO reservation : reservations) {
            if(isReservationActive(reservation)) {
                User user = userRepository.findByUsername(reservation.getClientUsername());
                if(user.getName().equals(clientName)) {
                    ReservationEntity entity = entityRepository.findEntityById(reservation.getEntityId());
                    return new ActiveReservationDTO(reservation.getEntityId(), reservation.getEntityName(), entity.getMaxPersons());
                }
            }
        }
        return null;
    }

    @Override
    public String getClientUsername(String entityName, Integer ownerId){
        List<ReservationDTO> reservations = findOwnerEntitiesReservations(ownerId);
        for (ReservationDTO reservation : reservations) {
            if(isReservationActive(reservation) && reservation.getEntityName().equals(entityName))
                return reservation.getClientUsername();
        }
        return "";
    }

    @Override
    public List<ReservationDTO> getClientReservations(Integer clientId) {
        List<ReservationDTO> reservationsDTO = new ArrayList<>();
        List<Reservation> reservations = reservationRepository.getClientReservations(clientId);
        for (Reservation r : reservations) {
            reservationsDTO.add(ReservationMapper.mapToDTO(r));
        }
        return reservationsDTO;
    }

    @Override
    public void makeReservation(ClientReservationDTO dto) {
        Reservation reservation = ReservationMapper.mapDTOToModel(dto);
        reservation.setClient(userRepository.getById(dto.getClientId()));
        reservation.setReservationEntity(entityRepository.findEntityById(dto.getEntityId()));
        reservationRepository.save(reservation);
        emailService.sendEmailAfterReservation(dto.getClientId());
    }

    @Override
    public List<ReservationDTO> getCurrentReservation(Date date, Integer clientId) {
        List<ReservationDTO> reservationsDTO = new ArrayList<>();
        List<Reservation> reservations = reservationRepository.getCurrentReservations(date, clientId);
        for (Reservation r : reservations) {
            reservationsDTO.add(ReservationMapper.mapToDTO(r));
        }
        return reservationsDTO;
    }

    @Override
    public void cancelReservation(Integer id) {
        reservationRepository.cancelReservation(id);
    }

    private boolean isReservationActive(ReservationDTO reservation){
        return reservation.getStartDate().before(new Date()) && reservation.getEndDate().after(new Date());
    }

    private ReservationEntity setReservationEntity(String entityName, Integer ownerId){
        User owner = userRepository.getById(ownerId);
        ReservationEntity entity = entityRepository.findOwnerEntityByName(entityName, ownerId);
        entity.setOwner(owner);
        return entity;
    }
}
