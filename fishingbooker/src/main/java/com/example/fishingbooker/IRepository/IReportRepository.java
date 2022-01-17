package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IReportRepository extends JpaRepository<Report, Integer> {
    @Query("update Report r set r.isPenaltyGiven=true where r.id=?1")
    @Modifying
    @Transactional
    void approvePenalty(Integer penaltyId);

    @Query("update Report r set r.isPenaltyRejected=true where r.id=?1")
    @Modifying
    @Transactional
    void rejectPenalty(Integer penaltyId);
}
