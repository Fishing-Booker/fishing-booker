package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.fishingEquipment.FishingEquipmentDTO;
import com.example.fishingbooker.Model.NavigationEquipment;

import java.util.List;

public interface INavigationEquipmentService {

    List<NavigationEquipment> findShipNavigationEquipment(Integer shipId);

    void addNavigationEquipment(FishingEquipmentDTO navEquipment, Integer shipId);

    void deleteNavigationEquipment(Integer equipmentId, Integer shipId);

}
