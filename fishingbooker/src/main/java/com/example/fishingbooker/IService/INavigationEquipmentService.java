package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.EquipmentDTO;
import com.example.fishingbooker.Model.NavigationEquipment;

import java.util.List;

public interface INavigationEquipmentService {

    List<NavigationEquipment> findShipNavigationEquipment(Integer shipId);

    void addNavigationEquipment(EquipmentDTO navEquipment, Integer shipId);

    void deleteNavigationEquipment(Integer equipmentId, Integer shipId);

}
