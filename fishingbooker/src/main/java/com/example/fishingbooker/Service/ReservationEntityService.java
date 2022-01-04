package com.example.fishingbooker.Service;

import com.example.fishingbooker.IService.IReservationEntityService;
import com.example.fishingbooker.Model.Image;
import com.example.fishingbooker.Model.Location;
import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.IRepository.IReservationEntityRepository;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationEntityService implements IReservationEntityService {

    @Autowired
    private IReservationEntityRepository entityRepository;

    public ReservationEntityService(){}

    @Override
    public List<ReservationEntity> findEntities() {
        return entityRepository.findAll();
    }

    @Override
    public ReservationEntity save(ReservationEntity entity) {
        return entityRepository.save(entity);
    }

    @Override
    public List<ReservationEntity> findOwnerEntities(int ownerId) {
        List<ReservationEntity> ownerEntities = new ArrayList<>();
        for (ReservationEntity entity : findEntities()) {
            if(entity.getOwner().getId() == ownerId && !entity.isDeleted()){
                ownerEntities.add(entity);
            }
        }
        return ownerEntities;
    }

    @Override
    public void deleteEntity(int entityId) {
        entityRepository.deleteById(entityId);
    }

    @Override
    public ReservationEntity editEntity(ReservationEntity entity) {
        //ReservationEntity updatedEntity = entityRepository.findById(entity.getId()).get();
        // set
        return entityRepository.save(entity);
    }

    @Override
    public ReservationEntity findEntityById(Integer entityId) {
        ReservationEntity entity = entityRepository.findEntityById(entityId);
        entity.setOwner(null);
        entity.setImages(null);
        return entity;
    }

}
