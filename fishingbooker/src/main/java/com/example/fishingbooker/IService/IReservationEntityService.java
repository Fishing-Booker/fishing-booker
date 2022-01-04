package com.example.fishingbooker.IService;

import com.example.fishingbooker.Model.ReservationEntity;

import java.util.List;

public interface IReservationEntityService {

    List<ReservationEntity> findEntities();

    ReservationEntity save(ReservationEntity entity);

    List<ReservationEntity> findOwnerEntities(int ownerId);

    void deleteEntity(int entityId);

    ReservationEntity editEntity(ReservationEntity entity);

    Integer setId();

    ReservationEntity findEntityById(Integer entityId);


}
