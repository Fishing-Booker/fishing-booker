package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.reservationAction.AddReservationActionDTO;
import com.example.fishingbooker.DTO.reservationAction.ReservationActionDTO;
import com.example.fishingbooker.Enum.ReservationType;
import com.example.fishingbooker.IRepository.IReservationActionRepository;
import com.example.fishingbooker.IRepository.IReservationEntityRepository;
import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.IService.IReservationActionService;
import com.example.fishingbooker.IService.ISubscriberService;
import com.example.fishingbooker.Model.ReservationAction;
import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
    private IReservationActionRepository actionRepository;

    @Autowired
    private ISubscriberService subscriberService;

    @Override
    public void save(AddReservationActionDTO dto){
        ReservationAction action = new ReservationAction();
        action.setStartDate(dto.getStartDate());
        action.setEndDate(dto.getEndDate());
        action.setMaxPersons(dto.getMaxPersons());
        action.setPrice(dto.getPrice());
        action.setReservationType(ReservationType.quickReservation);
        action.setClient(userRepository.getById(dto.getOwner()));
        action.setReservationEntity(setReservationEntity(dto.getEntityId(), dto.getOwner()));
        action.setAdditionalServices(dto.getAdditionalServices());
        action.setBooked(false);
        actionRepository.save(action);
    }

    @Override
    public List<ReservationActionDTO> findEntityActions(Integer entityId){
        List<ReservationActionDTO> actions = new ArrayList<>();
        for (ReservationAction action : actionRepository.findEntityActions(entityId)) {
            ReservationActionDTO dto = new ReservationActionDTO();
            dto.setActionId(action.getId());
            dto.setStartDate(action.getStartDate());
            dto.setEndDate(action.getEndDate());
            dto.setPrice(action.getPrice());
            dto.setAdditionalServices(action.getAdditionalServices());
            dto.setMaxPersons(action.getMaxPersons());
            dto.setBookedBy(getIsActionBooked(action));
            dto.setBooked(action.isBooked());
            actions.add(dto);
        }
        return actions;
    }

    @Override
    public void makeReservation(Integer actionId, Integer clientId) throws MessagingException, UnsupportedEncodingException {
        actionRepository.makeReservation(actionId, clientId);
        subscriberService.sendEmailWithActionReservationInfo(clientId);
    }

    @Override
    public List<ReservationActionDTO> getAvailableActions(Integer id) {
        List<ReservationActionDTO> availableActions = new ArrayList<>();
        for (ReservationAction action : actionRepository.findEntityActions(id)) {
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

    private String getIsActionBooked(ReservationAction action){
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
