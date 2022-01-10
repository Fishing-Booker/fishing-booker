package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.ReservationActionDTO;
import com.example.fishingbooker.Enum.ReservationType;
import com.example.fishingbooker.IRepository.IReservationActionRepository;
import com.example.fishingbooker.IRepository.IReservationEntityRepository;
import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.IService.IReservationActionService;
import com.example.fishingbooker.Model.ReservationAction;
import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReservationActionService implements IReservationActionService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IReservationEntityRepository entityRepository;

    @Autowired
    private IReservationActionRepository actionRepository;

    @Override
    public void save(ReservationActionDTO dto){
        ReservationAction action = new ReservationAction();
        action.setStartDate(dto.getStartDate());
        action.setEndDate(dto.getEndDate());
        action.setMaxPersons(dto.getMaxPersons());
        action.setPrice(dto.getPrice());
        action.setReservationType(ReservationType.quickReservation);
        action.setClient(userRepository.getById(dto.getOwner()));
        action.setReservationEntity(setReservationEntity(dto.getEntityId(), dto.getEntityId()));
        action.setAdditionalServices(dto.getAdditionalServices());
        actionRepository.save(action);
    }

    private ReservationEntity setReservationEntity(Integer entityId, Integer ownerId){
        User owner = userRepository.getById(ownerId);
        ReservationEntity entity = entityRepository.findEntityById(entityId);
        entity.setOwner(owner);
        return entity;
    }

}
