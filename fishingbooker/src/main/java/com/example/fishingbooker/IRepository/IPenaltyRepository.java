package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IPenaltyRepository extends JpaRepository<Penalty, Integer> {

    @Query("SELECT p FROM Penalty p WHERE p.client.id=?1")
    Penalty findClientPenalties(Integer clientId);

    @Query("UPDATE Penalty p SET p.penalties=?1 WHERE p.client.id=?2")
    @Modifying
    @Transactional
    void updatePenalty(Integer penalties, Integer clientId);

    @Modifying
    @Transactional
    @Query("UPDATE Penalty p SET p.penalties=0 WHERE p.client.id=?1")
    void annulPenalties(Integer clientId);
}
