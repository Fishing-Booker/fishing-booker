package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.fishingEquipment.AddFishingEquipmentDTO;
import com.example.fishingbooker.DTO.fishingEquipment.FishingEquipmentDTO;
import com.example.fishingbooker.IRepository.IFishingEquipmentRepository;
import com.example.fishingbooker.IRepository.IReservationEntityRepository;
import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.IService.IFishingEquipmentService;
import com.example.fishingbooker.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FishingEquipmentService implements IFishingEquipmentService {

    @Autowired
    IFishingEquipmentRepository fishingEquipmentRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IReservationEntityRepository entityRepository;

    @Override
    public List<FishingEquipmentDTO> findShipFishingEquipment(Integer shipId) {
        List<FishingEquipmentDTO> equipment = new ArrayList<>();
        for (FishingEquipment eq : fishingEquipmentRepository.findShipFishingEquipment(shipId)) {
            equipment.add(new FishingEquipmentDTO(eq.getId(), eq.getName()));
        }
        return equipment;
    }

    @Override
    public void addFishingEquipment(AddFishingEquipmentDTO fishEquipment) {
        FishingEquipment fishingEquipment = new FishingEquipment();
        fishingEquipment.setName(fishEquipment.getName());
        fishingEquipment.setReservationEntity(getEntity(fishEquipment.getEntityId(), fishEquipment.getOwnerId()));
        fishingEquipmentRepository.save(fishingEquipment);
    }

    @Override
    public void deleteFishingEquipment(Integer equipmentId, Integer shipId) {
        fishingEquipmentRepository.deleteFishingEquipment(equipmentId, shipId);
    }

    private ReservationEntity getEntity(Integer entityId, Integer ownerId){
        User owner = userRepository.getById(ownerId);
        ReservationEntity entity = entityRepository.findEntityById(entityId);
        entity.setOwner(owner);
        return entity;
    }
}
