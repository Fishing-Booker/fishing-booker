package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IComplaintRepository extends JpaRepository<Complaint, Integer> {
}
