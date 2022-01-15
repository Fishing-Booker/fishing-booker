package com.example.fishingbooker.Mapper;

import com.example.fishingbooker.DTO.lodge.LocationDTO;
import com.example.fishingbooker.DTO.lodge.OwnerDTO;
import com.example.fishingbooker.DTO.ship.ShipInfoDTO;
import com.example.fishingbooker.Model.Ship;

public class ShipMapper {
    public static ShipInfoDTO mapToDTO(Ship ship) {
        ShipInfoDTO dto = new ShipInfoDTO();
        dto.setId(ship.getId());
        dto.setName(ship.getName());
        dto.setDescription(ship.getDescription());
        dto.setAverageGrade(ship.getAverageGrade());
        dto.setRules(ship.getRules());
        dto.setCancelConditions(ship.getCancelConditions());
        dto.setCapacity(ship.getMaxPersons());
        dto.setLength(ship.getLength());
        dto.setMaxSpeed(ship.getMaxSpeed());
        dto.setLocation(new LocationDTO(ship.getLocation().getAddress(), ship.getLocation().getCity(), ship.getLocation().getCountry()));
        dto.setImages(null);
        dto.setOwner(new OwnerDTO(ship.getOwner().getName(), ship.getOwner().getSurname()));
        dto.setMaxPersons(ship.getMaxPersons());
        return dto;
    }
}
