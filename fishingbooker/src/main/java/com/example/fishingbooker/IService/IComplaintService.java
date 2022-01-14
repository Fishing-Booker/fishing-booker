package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.ComplaintDTO;
import com.example.fishingbooker.Model.Complaint;

public interface IComplaintService {
    Complaint addComplaint(ComplaintDTO dto);
}
