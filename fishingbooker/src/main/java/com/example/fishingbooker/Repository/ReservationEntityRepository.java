package com.example.fishingbooker.Repository;

import com.example.fishingbooker.Model.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationEntityRepository extends JpaRepository<ReservationEntity, Integer> {

    List<ReservationEntity> findEntitiesByType(String type);

    List<ReservationEntity> findUsersEntities(int userId);

    ReservationEntity editEntity(ReservationEntity entity);
}
