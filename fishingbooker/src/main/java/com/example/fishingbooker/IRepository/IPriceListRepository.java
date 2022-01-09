package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Enum.ServiceType;
import com.example.fishingbooker.Model.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IPriceListRepository extends JpaRepository<PriceList, Integer> {

    @Query("SELECT p FROM PriceList p WHERE p.reservationEntity.id=?1 and p.isDeleted=false")
    List<PriceList> findEntityPrices(Integer entityId);

    @Query("update PriceList p " + "set p.serviceName=?1, p.servicePrice=?2, p.serviceType=?3 "
            + "WHERE p.id=?4")
    @Modifying
    @Transactional
    void updatePrice(String name, double price, ServiceType serviceType, Integer priceId);

    @Query("SELECT p FROM PriceList p WHERE p.reservationEntity.id=?1 and p.id=?2")
    PriceList findEntityPrice(Integer entityId, Integer priceId);

    @Query("update PriceList p " + "set p.isDeleted=true " + "WHERE p.id=?1")
    @Modifying
    @Transactional
    void deletePrice(Integer priceId);

}
