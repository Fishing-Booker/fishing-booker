package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.reservationPeriod.ReservationPeriodDTO;
import com.example.fishingbooker.IRepository.IReservationPeriodRepository;
import com.example.fishingbooker.IService.IReservationEntityService;
import com.example.fishingbooker.IService.IReservationPeriodService;
import com.example.fishingbooker.IService.IReservationService;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Model.Reservation;
import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.Model.ReservationPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationPeriodService implements IReservationPeriodService {

    @Autowired
    private IReservationPeriodRepository repository;

    @Autowired
    private IReservationEntityService entityService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IReservationService reservationService;

    @Override
    public void save(ReservationPeriodDTO dto) {
        ReservationPeriod newPeriod = new ReservationPeriod();
        newPeriod.setStartDate(dto.getStartDate());
        newPeriod.setEndDate(dto.getEndDate());
        newPeriod.setReservationEntity(getEntity(dto.getEntityId()));
        repository.save(newPeriod);
    }

    @Override
    public List<ReservationPeriod> findAllPeriods(Integer entityId) {
        return repository.findAllPeriods(entityId);
    }

    @Override
    public List<ReservationPeriod> findFreePeriods(Integer entityId) {
        List<ReservationPeriod> allPeriods = findAllPeriods(entityId);
        List<Reservation> allReservations = reservationService.findEntityReservations(entityId);
        List<ReservationPeriod> freePeriods = new ArrayList<>();
        return freePeriods;
    }

    private ReservationEntity getEntity(Integer id){
        ReservationEntity entity = entityService.findEntityById(id);
        entity.setOwner(userService.findUserById(1));
        return entity;
    }
}
