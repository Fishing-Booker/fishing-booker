package com.example.fishingbooker.Service;

import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.Repository.ReservationEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationEntityService {

    @Autowired
    private ReservationEntityRepository entityRepository;

    public ReservationEntity addEntity(ReservationEntity entity){
        return entityRepository.save(entity);
    }

    public List<ReservationEntity> getEntities(){
        return entityRepository.findAll();
    }

    public List<ReservationEntity> getLodges(){
        return entityRepository.findEntitiesByType("lodge");
    }

    public List<ReservationEntity> getUsersEntities(int userId){
        return entityRepository.findUsersEntities(userId);
    }

    public ReservationEntity editEntity(ReservationEntity entity){
        return entityRepository.editEntity(entity);
    }

    public void deleteEntity(int entityId){
        entityRepository.deleteById(entityId);
    }



}
