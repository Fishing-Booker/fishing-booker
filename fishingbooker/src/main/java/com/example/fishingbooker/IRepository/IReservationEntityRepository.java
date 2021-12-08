package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReservationEntityRepository extends JpaRepository<ReservationEntity, Integer> {

}
