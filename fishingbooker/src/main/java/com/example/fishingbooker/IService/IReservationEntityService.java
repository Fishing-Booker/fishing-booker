package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.ReservationEntityDTO;
import com.example.fishingbooker.Model.ReservationEntity;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface IReservationEntityService {

    List<ReservationEntity> findEntities();

    ReservationEntity save(ReservationEntity entity);

    List<ReservationEntity> findOwnerEntities(int ownerId);

    void deleteEntity(int entityId);

    ReservationEntity editEntity(ReservationEntity entity);

    Integer setId();

    ReservationEntityDTO findEntityById(Integer entityId);

    ReservationEntity getEntityById(Integer entityId);

    Integer getOwnerId(Integer entityId);

    ReservationEntity findOwnerEntityByName(String entityName, Integer ownerId);

    void updateEntityAverageGrade(Integer entityId, double updatedGrade);
}
