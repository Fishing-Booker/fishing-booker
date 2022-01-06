package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.reservationPeriod.ReservationPeriodDTO;
import com.example.fishingbooker.IRepository.IReservationPeriodRepository;
import com.example.fishingbooker.IService.*;
import com.example.fishingbooker.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationPeriodService implements IReservationPeriodService {

    @Autowired
    private IReservationPeriodRepository repository;

    @Autowired
    private ReservationEntityService entityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ILodgeService lodgeService;

    @Override
    public void save(ReservationPeriodDTO dto) {
        ReservationPeriod newPeriod = new ReservationPeriod();
        newPeriod.setStartDate(dto.getStartDate());
        newPeriod.setEndDate(dto.getEndDate());
        ReservationEntity entity = entityService.findEntityById(dto.getEntityId());
        User owner = userService.findByUsername("sara");
        entity.setOwner(owner);
        newPeriod.setReservationEntity(entity);
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
        List<ReservationPeriod> freePeriods = allPeriods;
        for (Reservation reservation : allReservations) {
            freePeriods = getChangedPeriods(freePeriods, reservation);
        }
        return freePeriods;
    }

    private List<ReservationPeriod> getChangedPeriods(List<ReservationPeriod> periods, Reservation reservation){
        List<ReservationPeriod> newPeriods = new ArrayList<>();
        for (ReservationPeriod period : periods) {
            if(period.getStartDate().before(reservation.getStartDate()) && period.getEndDate().after(reservation.getEndDate())){
                if(period.getStartDate().before(reservation.getStartDate())){
                    ReservationEntity entity = new ReservationEntity();
                    entity.setOwner(new User());
                    ReservationPeriod newPeriod = new ReservationPeriod(0, period.getStartDate(), reservation.getStartDate(), entity);
                    newPeriods.add(newPeriod);
                }
                if(reservation.getEndDate().before(period.getEndDate())){
                    ReservationEntity entity = new ReservationEntity();
                    entity.setOwner(new User());
                    ReservationPeriod newPeriod = new ReservationPeriod(0, reservation.getEndDate(), period.getEndDate(), entity);
                    newPeriods.add(newPeriod);
                }
            } else {
                ReservationEntity entity = new ReservationEntity();
                entity.setOwner(new User());
                ReservationPeriod newPeriod = new ReservationPeriod(0, period.getStartDate(), period.getEndDate(), entity);
                newPeriods.add(newPeriod);
            }
        }
        return newPeriods;
    }

    private ReservationEntity getEntity(Integer id){
        Lodge l = lodgeService.findById(id);
        ReservationEntity entity = new ReservationEntity(id, l.getOwner(), l.getName(), l.getLocation(),
                l.getDescription(), l.getRules(), l.getCancelConditions(), l.getAverageGrade(), 10, l.getImages());
        User owner = userService.findUserById(1);
        entity.setOwner(owner);
        return entity;
    }
}
