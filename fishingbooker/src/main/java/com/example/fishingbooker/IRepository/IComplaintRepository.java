package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IComplaintRepository extends JpaRepository<Complaint, Integer> {

    @Query("SELECT c FROM Complaint c ")
    List<Complaint> getAll();

    @Query("update Complaint c set c.isResponded=true where c.id=?1")
    @Modifying
    @Transactional
    void updateComplaintToResponded(Integer id);
}
