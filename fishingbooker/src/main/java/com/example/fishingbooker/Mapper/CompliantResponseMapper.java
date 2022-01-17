package com.example.fishingbooker.Mapper;

import com.example.fishingbooker.DTO.CompliantResponseDTO;
import com.example.fishingbooker.Model.ComplaintResponse;

public class CompliantResponseMapper {
    public static ComplaintResponse mapDTOtoModel(CompliantResponseDTO dto) {
        ComplaintResponse complaintResponse = new ComplaintResponse();
        complaintResponse.setResponse(dto.getText());
        return complaintResponse;
    }
}
