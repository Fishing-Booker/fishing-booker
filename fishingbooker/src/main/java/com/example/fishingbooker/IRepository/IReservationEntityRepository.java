package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReservationEntityRepository extends JpaRepository<ReservationEntity, Integer> {

    @Query("SELECT e FROM ReservationEntity e WHERE e.id=?1")
    ReservationEntity findEntityById(Integer entityId);

}
