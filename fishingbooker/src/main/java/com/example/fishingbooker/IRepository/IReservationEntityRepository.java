package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Lodge;
import com.example.fishingbooker.Model.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IReservationEntityRepository extends JpaRepository<ReservationEntity, Integer> {

    @Query("SELECT e FROM ReservationEntity e WHERE e.id=?1")
    ReservationEntity findEntityById(Integer entityId);

    @Query("SELECT e FROM ReservationEntity e WHERE e.owner.id=?1 and e.isDeleted=false")
    List<ReservationEntity> findOwnerEntities(Integer ownerId);

    @Query("SELECT e.name FROM ReservationEntity e WHERE e.id=?1")
    String getEntityName(Integer entityId);

    @Query("SELECT e FROM ReservationEntity e WHERE e.name=?1 AND e.owner.id=?2")
    ReservationEntity findOwnerEntityByName(String entityName, Integer ownerId);

}
