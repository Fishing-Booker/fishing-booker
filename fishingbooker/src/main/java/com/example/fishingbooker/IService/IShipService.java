package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.UpdateShipDTO;
import com.example.fishingbooker.Model.Ship;

import java.util.List;

public interface IShipService {

    Ship save(Ship ship);

    List<Ship> findOwnerShips(Integer ownerId);

    void deleteShip(Integer shipId);

    Ship findById(Integer shipId);

    void updateShip(UpdateShipDTO dto, Integer shipId);

}
