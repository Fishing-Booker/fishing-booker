package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.LoyaltyProgramme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ILoyaltyProgrammeRepository extends JpaRepository<LoyaltyProgramme, Integer> {
    LoyaltyProgramme save(LoyaltyProgramme loyaltyProgramme);

    @Query("delete from LoyaltyProgramme l where l.id=?1")
    @Modifying
    @Transactional
    void deleteLoyaltyProgramme(Integer id);
}
