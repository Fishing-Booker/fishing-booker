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
        //return entityRepository.findAll();

        List<ReservationEntity> entities = new ArrayList<>();
        User lodgeOwner = new User(1, "sara", "sara");
        Location location = new Location(1, 20.5, 20.5, "Temerinska",
                "Novi Sad", "Serbia");
        List<Image> images = new ArrayList<>();
        ReservationEntity entity1 = new ReservationEntity(1, lodgeOwner, "Lodge1", location,
                "No description", "No rules", "No cancel conditions", 5.0, images);
        ReservationEntity entity2 = new ReservationEntity(1, lodgeOwner, "Lodge1", location,
                "No description", "No rules", "No cancel conditions", 5.0, images);
        ReservationEntity entity3 = new ReservationEntity(1, lodgeOwner, "Lodge1", location,
                "No description", "No rules", "No cancel conditions", 5.0, images);

        entities.add(entity1);
        entities.add(entity2);
        entities.add(entity3);

        return entities;
    }

    @Override
    public ReservationEntity addEntity(ReservationEntity entity) {
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
        ReservationEntity updatedEntity = entityRepository.findById(entity.getId()).get();
        // set
        return entityRepository.save(entity);
    }
}
