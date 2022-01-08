package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPriceListRepository extends JpaRepository<PriceList, Integer> {

    @Query("SELECT p FROM PriceList p WHERE p.reservationEntity.id=?1 and p.isDeleted=false")
    List<PriceList> findEntityPrices(Integer entityId);

}
