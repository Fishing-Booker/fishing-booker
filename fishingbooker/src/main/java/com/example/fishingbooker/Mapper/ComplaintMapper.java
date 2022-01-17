package com.example.fishingbooker.Mapper;

import com.example.fishingbooker.DTO.ComplaintDTO;
import com.example.fishingbooker.DTO.ComplaintInfoDTO;
import com.example.fishingbooker.Model.Complaint;
import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.Model.User;

public class ComplaintMapper {

    public static Complaint mapDTOToModel(ComplaintDTO dto) {
        Complaint complaint = new Complaint();
        complaint.setText(dto.getText());
        return complaint;
    }

    public static ComplaintInfoDTO mapModelToDTO(Complaint complaint, ReservationEntity entity, User client) {
        ComplaintInfoDTO dto = new ComplaintInfoDTO();
        dto.setText(complaint.getText());
        dto.setEntityName(entity.getName());
        dto.setEntityOwner(entity.getOwner().getName() + " " + entity.getOwner().getSurname());
        dto.setAverageGrade(entity.getAverageGrade());
        dto.setClientName(client.getName());
        dto.setClientSurname(client.getSurname());
        dto.setClientEmail(client.getEmail());
        dto.setClientUsername(client.getUsername());
        dto.setClientPhone(client.getPhoneNumber());
        return dto;
    }
}
