package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.UpdateShipDTO;
import com.example.fishingbooker.IRepository.IShipRepository;
import com.example.fishingbooker.IService.ILocationService;
import com.example.fishingbooker.IService.IShipService;
import com.example.fishingbooker.Model.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipService implements IShipService {

    @Autowired
    private IShipRepository shipRepository;

    @Autowired
    private ILocationService locationService;

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

    @Override
    public void deleteShip(Integer shipId) {
        shipRepository.deleteShip(shipId);
    }

    @Override
    public Ship findById(Integer shipId) {
        Ship ship = shipRepository.findShipById(shipId);
        ship.setOwner(null);
        ship.setImages(null);
        return ship;
    }

    @Override
    public void updateShip(UpdateShipDTO dto, Integer shipId) {
        locationService.updateLocation(dto.getAddress(), dto.getCity(), dto.getCountry(), dto.getLocationId());
        shipRepository.updateShip(dto.getName(), dto.getDescription(), dto.getShipType(), dto.getLength(),
                dto.getEngineNumber(), dto.getEnginePower(), dto.getMaxSpeed(), dto.getCapacity(), shipId);
    }
}
