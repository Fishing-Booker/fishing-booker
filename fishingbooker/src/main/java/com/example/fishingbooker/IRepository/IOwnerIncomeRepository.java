package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.OwnerIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IOwnerIncomeRepository extends JpaRepository<OwnerIncome, Integer> {

    @Query("select ownerIncome from OwnerIncome ownerIncome where ownerIncome.owner.id=?1")
    OwnerIncome findOwnerIncomeByOwnerId(Integer ownerId);
}
