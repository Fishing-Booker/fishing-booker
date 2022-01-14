package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.UpdateShipDTO;
import com.example.fishingbooker.DTO.ship.ShipDTO;
import com.example.fishingbooker.DTO.ship.ShipInfoDTO;
import com.example.fishingbooker.Model.Ship;

import java.io.IOException;
import java.util.List;

public interface IShipService {

    Ship save(Ship ship);

    List<ShipDTO> findOwnerShips(Integer ownerId) throws IOException;

    void deleteShip(Integer shipId);

    Ship findById(Integer shipId);

    void updateShip(UpdateShipDTO dto, Integer shipId);

    List<String> findShipRules(Integer shipId);

    void addRule(String rule, Integer shipId);

    void deleteRule(Integer ruleIndex, Integer lodgeId);

    List<ShipInfoDTO> getAll();

    List<String> getFirstLetters();

    List<ShipInfoDTO> searchAndFilter(String name, String letter);

    ShipInfoDTO getById(Integer id);
}
