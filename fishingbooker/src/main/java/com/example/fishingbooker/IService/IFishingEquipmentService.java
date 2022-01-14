package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.fishingEquipment.AddFishingEquipmentDTO;
import com.example.fishingbooker.DTO.fishingEquipment.FishingEquipmentDTO;

import java.util.List;

public interface IFishingEquipmentService {

    List<FishingEquipmentDTO> findShipFishingEquipment(Integer shipId);

    void addFishingEquipment(AddFishingEquipmentDTO fishEquipment);

    void deleteFishingEquipment(Integer equipmentId, Integer shipId);
}
