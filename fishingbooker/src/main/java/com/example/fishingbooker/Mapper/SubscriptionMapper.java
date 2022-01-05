package com.example.fishingbooker.Mapper;

import com.example.fishingbooker.DTO.SubscriptionDTO;
import com.example.fishingbooker.DTO.lodge.LocationDTO;
import com.example.fishingbooker.DTO.lodge.OwnerDTO;
import com.example.fishingbooker.Model.ReservationEntity;

public class SubscriptionMapper {

    public static SubscriptionDTO mapToDTO(ReservationEntity entity) {
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setAverageGrade(entity.getAverageGrade());
        dto.setImages(null);
        dto.setRules(entity.getRules());
        dto.setCancelConditions(entity.getCancelConditions());
        dto.setLocation(new LocationDTO(entity.getLocation().getAddress(), entity.getLocation().getCity(), entity.getLocation().getCountry()));
        dto.setOwner(new OwnerDTO(entity.getOwner().getName(), entity.getOwner().getSurname()));
        return dto;
    }
}
