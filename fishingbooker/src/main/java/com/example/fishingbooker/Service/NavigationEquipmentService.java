package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.EquipmentDTO;
import com.example.fishingbooker.IRepository.INavigationEquipmentRepository;
import com.example.fishingbooker.IService.INavigationEquipmentService;
import com.example.fishingbooker.IService.IShipService;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Model.NavigationEquipment;
import com.example.fishingbooker.Model.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NavigationEquipmentService implements INavigationEquipmentService {

    @Autowired
    private INavigationEquipmentRepository navigationEquipmentRepository;

    @Autowired
    private IShipService shipService;

    @Autowired
    private IUserService userService;

    @Override
    public List<NavigationEquipment> findShipNavigationEquipment(Integer shipId) {
        List<NavigationEquipment> navigationEquipment = navigationEquipmentRepository.findShipNavigationEquipment(shipId);
        for (NavigationEquipment eq : navigationEquipment) {
            eq.setShip(null);
        }
        return navigationEquipment;
    }

    @Override
    public void addNavigationEquipment(EquipmentDTO navEquipment, Integer shipId) {
        NavigationEquipment navigationEquipment = new NavigationEquipment();
        navigationEquipment.setName(navEquipment.getEquipment());
        navigationEquipment.setShip(getShip(shipId, navEquipment.getOwner()));
        navigationEquipmentRepository.save(navigationEquipment);
    }

    @Override
    public void deleteNavigationEquipment(Integer equipmentId, Integer shipId) {
        navigationEquipmentRepository.deleteNavigationEquipment(equipmentId, shipId);
    }

    private Ship getShip(Integer shipId, Integer ownerId){
        Ship ship = shipService.findById(shipId);
        ship.setOwner(userService.findUserById(ownerId));
        ship.setImages(new ArrayList<>());
        return ship;
    }
}
