package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.EquipmentDTO;
import com.example.fishingbooker.IRepository.IFishingEquipmentRepository;
import com.example.fishingbooker.IService.IFishingEquipmentService;
import com.example.fishingbooker.IService.IReservationEntityService;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Model.FishingEquipment;
import com.example.fishingbooker.Model.NavigationEquipment;
import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.Model.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FishingEquipmentService implements IFishingEquipmentService {

    @Autowired
    private IFishingEquipmentRepository fishingEquipmentRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IReservationEntityService entityService;

    @Override
    public List<FishingEquipment> findShipFishingEquipment(Integer shipId) {
        List<FishingEquipment> fishingEquipment = fishingEquipmentRepository.findShipFishingEquipment(shipId);
        for (FishingEquipment eq : fishingEquipment) {
            eq.setReservationEntity(null);
        }
        return fishingEquipment;
    }

    @Override
    public void addFishingEquipment(EquipmentDTO fishEquipment, Integer shipId) {
        FishingEquipment fishingEquipment = new FishingEquipment();
        fishingEquipment.setName(fishEquipment.getEquipment());
        fishingEquipment.setReservationEntity(getEntity(shipId, fishEquipment.getOwner()));
        fishingEquipmentRepository.save(fishingEquipment);
    }

    @Override
    public void deleteFishingEquipment(Integer equipmentId, Integer shipId) {
        fishingEquipmentRepository.deleteFishingEquipment(equipmentId, shipId);
    }

    private ReservationEntity getEntity(Integer shipId, Integer ownerId){
        ReservationEntity entity = entityService.findEntityById(shipId);
        entity.setOwner(userService.findUserById(ownerId));
        entity.setImages(new ArrayList<>());
        return entity;
    }
}
