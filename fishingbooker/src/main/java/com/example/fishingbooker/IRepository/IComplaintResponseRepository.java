package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.ComplaintResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IComplaintResponseRepository extends JpaRepository<ComplaintResponse, Integer> {
}
