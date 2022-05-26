package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.ship.UpdateShipDTO;
import com.example.fishingbooker.DTO.ship.ShipDTO;
import com.example.fishingbooker.DTO.ship.ShipInfoDTO;
import com.example.fishingbooker.Model.Shio;
import com.example.fishingbooker.Model.Ship;

import java.util.Date;
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

    void deleteRule(Integer ruleIndex, Integer shipId);

    List<ShipInfoDTO> getAll();

    List<String> getFirstLetters();

    List<ShipInfoDTO> searchAndFilter(String name, String letter, String location, Integer grade);

    ShipInfoDTO getById(Integer id);

    List<ShipInfoDTO> getByReservationDate(Date date);

    List<String> findShipNavEquipment(Integer shipId);

    void addNavEquipment(String equipment, Integer shipId);

    void deleteNavEquipment(Integer eqIndex, Integer shipId);

    List<String> getOwnerShipNames(Integer ownerId);

    List<ShipDTO> searchShipsByName(String name, Integer owner) throws IOException;

    List<ShipInfoDTO> sortShips(String type);

    Ship fetchById(Integer id);

    void deleteById(Integer id);

    void updateShip(Ship ship);
}
