package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.ReservationEntityDTO;
import com.example.fishingbooker.IService.IReservationEntityService;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Model.Image;
import com.example.fishingbooker.Model.Location;
import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.IRepository.IReservationEntityRepository;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ReservationEntityService implements IReservationEntityService {

    @Autowired
    private IReservationEntityRepository entityRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<ReservationEntity> findEntities() {
        return entityRepository.findAll();
    }

    @Override
    public ReservationEntity save(ReservationEntity entity) {
        return entityRepository.save(entity);
    }

    @Override
    public List<ReservationEntity> findOwnerEntities(int ownerId) {
        List<ReservationEntity> ownerEntities = new ArrayList<>();
        for (ReservationEntity entity : findEntities()) {
            if(entity.getOwner().getId() == ownerId && !entity.isDeleted()){
                ownerEntities.add(entity);
            }
        }
        return ownerEntities;
    }

    @Override
    public void deleteEntity(int entityId) {
        entityRepository.deleteById(entityId);
    }

    @Override
    public ReservationEntity editEntity(ReservationEntity entity) {
        //ReservationEntity updatedEntity = entityRepository.findById(entity.getId()).get();
        // set
        return entityRepository.save(entity);
    }

    @Override
    public Integer setId() {
        List<ReservationEntity> entities = entityRepository.findAll();
        if(entities.size() != 0) {
            ReservationEntity entity = entities.get(entities.size() - 1);
            return entity.getId() + 1;
        } else {
            return 1;
        }
    }

    @Override
    public ReservationEntityDTO findEntityById(Integer entityId) {
        ReservationEntity entity = entityRepository.findEntityById(entityId);
        return new ReservationEntityDTO(entityId, entity.getName(), entity.getLocation().getId(),
                entity.getDescription(), entity.getRules(), entity.getCancelConditions(), entity.getAverageGrade(),
                entity.getMaxPersons());
    }

    @Override
    public ReservationEntity getEntityById(Integer entityId) {
        return entityRepository.findEntityById(entityId);
    }

    @Override
    public Integer getOwnerId(Integer entityId) {
        return entityRepository.getOwnerId(entityId);
    }

    @Override
    public ReservationEntity findOwnerEntityByName(String entityName, Integer ownerId) {
        return entityRepository.findOwnerEntityByName(entityName, ownerId);
    }

    @Override
    public void updateEntityAverageGrade(Integer entityId, double updatedGrade){
        entityRepository.updateGrade(updatedGrade, entityId);
    }
}
