package com.example.fishingbooker.Mapper;

import com.example.fishingbooker.DTO.lodge.LocationDTO;
import com.example.fishingbooker.DTO.lodge.LodgeInfoDTO;
import com.example.fishingbooker.DTO.lodge.OwnerDTO;
import com.example.fishingbooker.Model.Lodge;

public class LodgeMapper {
    public static LodgeInfoDTO mapToDTO(Lodge lodge) {
        LodgeInfoDTO dto = new LodgeInfoDTO();
        dto.setId(lodge.getId());
        dto.setName(lodge.getName());
        dto.setDescription(lodge.getDescription());
        dto.setAverageGrade(lodge.getAverageGrade());
        dto.setRules(lodge.getRules());
        dto.setCancelConditions(lodge.getCancelConditions());
        dto.setLocation(new LocationDTO(lodge.getLocation().getAddress(), lodge.getLocation().getCity(), lodge.getLocation().getCountry()));
        dto.setBedroom(null);
        dto.setImages(null);
        dto.setOwner(new OwnerDTO(lodge.getOwner().getName(), lodge.getOwner().getSurname()));
        return dto;
    }
}
