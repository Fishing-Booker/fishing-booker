package com.example.fishingbooker.IService;

import com.example.fishingbooker.Model.Ship;

import java.util.List;

public interface IShipService {

    Ship save(Ship ship);

    List<Ship> findOwnerShips(Integer ownerId);

}
