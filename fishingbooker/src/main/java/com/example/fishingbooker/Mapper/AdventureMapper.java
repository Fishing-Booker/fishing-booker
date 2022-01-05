package com.example.fishingbooker.Mapper;

import com.example.fishingbooker.DTO.adventure.AdventureInfoDTO;
import com.example.fishingbooker.DTO.lodge.LocationDTO;
import com.example.fishingbooker.DTO.lodge.OwnerDTO;
import com.example.fishingbooker.Model.Adventure;

public class AdventureMapper {
    public static AdventureInfoDTO mapToDTO(Adventure adventure) {
        AdventureInfoDTO dto = new AdventureInfoDTO();
        dto.setName(adventure.getName());
        dto.setAverageGrade(adventure.getAverageGrade());
        dto.setDescription(adventure.getDescription());
        dto.setImages(null);
        dto.setLocation(new LocationDTO(adventure.getLocation().getAddress(), adventure.getLocation().getCity(), adventure.getLocation().getCountry()));
        dto.setRules(adventure.getRules());
        dto.setCancelConditions(adventure.getCancelConditions());
        dto.setBiography(adventure.getBiography());
        dto.setMaxPersons(adventure.getMaxPersons());
        dto.setOwner(new OwnerDTO(adventure.getOwner().getName(), adventure.getOwner().getSurname()));
        return dto;
    }
}
