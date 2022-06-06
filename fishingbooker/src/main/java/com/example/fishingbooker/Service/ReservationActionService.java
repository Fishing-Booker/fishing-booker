package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.reservation.ReservationDTO;
import com.example.fishingbooker.DTO.reservationAction.AddReservationActionDTO;
import com.example.fishingbooker.DTO.reservationAction.ReservationActionDTO;
import com.example.fishingbooker.Enum.ReservationType;
import com.example.fishingbooker.IRepository.IReservationEntityRepository;
import com.example.fishingbooker.IRepository.IReservationRepository;
import com.example.fishingbooker.IRepository.IShipOwnerReservationRepository;
import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.IService.IReservationActionService;
import com.example.fishingbooker.IService.IReservationService;
import com.example.fishingbooker.IService.ISubscriberService;
import com.example.fishingbooker.Model.Reservation;
import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.Model.ShipOwnerReservation;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.dialect.lock.OptimisticEntityLockException;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ReservationActionService implements IReservationActionService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IReservationEntityRepository entityRepository;

    @Autowired
    private ISubscriberService subscriberService;

    @Autowired
    private IReservationRepository reservationRepository;

    @Autowired
    private IShipOwnerReservationRepository ownerRepository;

    @Autowired
    private IReservationService reservationService;

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public Reservation save(AddReservationActionDTO dto){
        Reservation action = new Reservation();
        action.setStartDate(dto.getStartDate());
        action.setEndDate(dto.getEndDate());
        action.setMaxPersons(dto.getMaxPersons());
        action.setPrice(dto.getPrice());
        action.setReservationType(ReservationType.quickReservation);
        action.setClient(userRepository.getById(dto.getOwner()));
        action.setAdditionalServices(dto.getAdditionalServices());
        action.setRegularService(dto.getRegularService());
        action.setBooked(false);
        try {
            ReservationEntity entity = entityRepository.getLocked(dto.getEntityId());
            action.setReservationEntity(entity);
            if(isPeriodAvailable(dto.getStartDate(), dto.getEndDate(), entity.getId())){
                return reservationRepository.save(action);
            } else {
                throw  new PessimisticLockingFailureException("Entity already reserved");
            }
        } catch (PessimisticLockingFailureException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    private boolean isPeriodAvailable(Date startDate, Date endDate, Integer entityId) {
        List<ReservationDTO> reservations = reservationService.findEntityReservations(entityId);
        for(ReservationDTO reservation : reservations) {
            if(reservation.getStartDate().before(startDate) && startDate.before(reservation.getEndDate())){
                return false;
            }
            if(reservation.getStartDate().before(endDate) && endDate.before(reservation.getEndDate())) {
                return false;
            }
            if(reservation.getStartDate().before(startDate) && endDate.before(reservation.getEndDate())) {
                return false;
            }
        }
        return true;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void addShipOwnerAction(Reservation reservation, Integer ownerId){
        try {
            User owner = userRepository.getLocker(ownerId);
            ShipOwnerReservation newReservation = new ShipOwnerReservation();
            newReservation.setReservation(reservation);
            newReservation.setOwner(owner);
            ownerRepository.save(newReservation);
        } catch (PessimisticLockingFailureException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<ReservationActionDTO> findEntityActions(Integer entityId){
        List<ReservationActionDTO> actions = new ArrayList<>();
        for (Reservation action : reservationRepository.findEntityActions(entityId, ReservationType.quickReservation)) {
            ReservationActionDTO dto = new ReservationActionDTO();
            dto.setActionId(action.getId());
            dto.setStartDate(action.getStartDate());
            dto.setEndDate(action.getEndDate());
            dto.setPrice(action.getPrice());
            dto.setAdditionalServices(action.getAdditionalServices());
            dto.setMaxPersons(action.getMaxPersons());
            dto.setBookedBy(getIsActionBooked(action));
            dto.setBooked(action.isBooked());
            dto.setRegularService(action.getRegularService());
            actions.add(dto);
        }
        return actions;
    }

    @Override
    public void makeReservation(Integer actionId, Integer clientId) throws MessagingException, UnsupportedEncodingException {
        try {
            reservationRepository.makeReservation(actionId, clientId);
            subscriberService.sendEmailWithActionReservationInfo(clientId);
        } catch (OptimisticEntityLockException e) {
            logger.debug("Optimistic lock exception");
        }
    }

    @Override
    public List<ReservationActionDTO> getAvailableActions(Integer id) {
        List<ReservationActionDTO> availableActions = new ArrayList<>();
        for (Reservation action : reservationRepository.findEntityActions(id, ReservationType.quickReservation)) {
            if (action.isBooked() == false) {
                ReservationActionDTO dto = new ReservationActionDTO();
                dto.setActionId(action.getId());
                dto.setStartDate(action.getStartDate());
                dto.setEndDate(action.getEndDate());
                dto.setPrice(action.getPrice());
                dto.setAdditionalServices(action.getAdditionalServices());
                dto.setMaxPersons(action.getMaxPersons());
                dto.setBookedBy(getIsActionBooked(action));
                dto.setBooked(action.isBooked());
                availableActions.add(dto);
            }
        }
        return availableActions;
    }

    @Override
    public void deleteAction(Integer id) {
        reservationRepository.deleteById(id);
    }

    private String getIsActionBooked(Reservation action){
        if(Objects.equals(action.getReservationEntity().getOwner().getId(), action.getClient().getId())){
            return "free";
        } else {
            return action.getClient().getUsername();
        }
    }

    private ReservationEntity setReservationEntity(Integer entityId, Integer ownerId){
        User owner = userRepository.getById(ownerId);
        ReservationEntity entity = entityRepository.findEntityById(entityId);
        entity.setOwner(owner);
        return entity;
    }

}
