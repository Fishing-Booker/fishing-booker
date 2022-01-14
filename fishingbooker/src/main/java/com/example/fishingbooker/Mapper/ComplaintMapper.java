package com.example.fishingbooker.Mapper;

import com.example.fishingbooker.DTO.ComplaintDTO;
import com.example.fishingbooker.Model.Complaint;

public class ComplaintMapper {

    public static Complaint mapDTOToModel(ComplaintDTO dto) {
        Complaint complaint = new Complaint();
        complaint.setText(dto.getText());
        return complaint;
    }
}
