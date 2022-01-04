package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.EquipmentDTO;
import com.example.fishingbooker.Model.FishingEquipment;

import java.util.List;

public interface IFishingEquipmentService {

    List<FishingEquipment> findShipFishingEquipment(Integer shipId);

    void addFishingEquipment(EquipmentDTO fishEquipment, Integer shipId);

    void deleteFishingEquipment(Integer equipmentId, Integer shipId);
}
