package com.example.fishingbooker.Service;

import com.example.fishingbooker.IRepository.IShipRepository;
import com.example.fishingbooker.IService.IShipService;
import com.example.fishingbooker.Model.Lodge;
import com.example.fishingbooker.Model.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipService implements IShipService {

    @Autowired
    private IShipRepository shipRepository;

    @Override
    public Ship save(Ship ship) {
        return shipRepository.save(ship);
    }

    @Override
    public List<Ship> findOwnerShips(Integer ownerId) {
        List<Ship> ships = shipRepository.findOwnerShips(ownerId);
        for (Ship s : ships) {
            s.setOwner(null);
            s.setImages(null);
        }
        return ships;
    }
}
