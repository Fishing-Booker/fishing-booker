package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.ComplaintDTO;
import com.example.fishingbooker.DTO.ComplaintInfoDTO;
import com.example.fishingbooker.Model.Complaint;

import java.util.List;

public interface IComplaintService {
    Complaint addComplaint(ComplaintDTO dto);

    List<ComplaintInfoDTO> getAll();
}
