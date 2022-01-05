package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.reservationPeriod.ReservationPeriodDTO;
import com.example.fishingbooker.IRepository.IReservationPeriodRepository;
import com.example.fishingbooker.IService.IReservationEntityService;
import com.example.fishingbooker.IService.IReservationPeriodService;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.Model.ReservationPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationPeriodService implements IReservationPeriodService {

    @Autowired
    private IReservationPeriodRepository repository;

    @Autowired
    private IReservationEntityService entityService;

    @Autowired
    private IUserService userService;

    @Override
    public void save(ReservationPeriodDTO dto) {
        ReservationPeriod newPeriod = new ReservationPeriod();
        newPeriod.setStartDate(dto.getStartDate());
        newPeriod.setEndDate(dto.getEndDate());
        newPeriod.setReservationEntity(getEntity(dto.getEntityId()));
        repository.save(newPeriod);
    }

    private ReservationEntity getEntity(Integer id){
        ReservationEntity entity = entityService.findEntityById(id);
        entity.setOwner(userService.findUserById(1));
        return entity;
    }
}
