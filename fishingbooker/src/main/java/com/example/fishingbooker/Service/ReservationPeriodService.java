package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.reservation.ReservationDTO;
import com.example.fishingbooker.DTO.reservationPeriod.AddReservationPeriodDTO;
import com.example.fishingbooker.DTO.reservationPeriod.GetReservationPeriodDTO;
import com.example.fishingbooker.DTO.reservationPeriod.ReservationPeriodDTO;
import com.example.fishingbooker.DTO.reservationPeriodOwner.ReservationPeriodOwnerDTO;
import com.example.fishingbooker.IRepository.IReservationEntityRepository;
import com.example.fishingbooker.IRepository.IReservationPeriodRepository;
import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.IService.*;
import com.example.fishingbooker.Model.*;
import org.hibernate.internal.util.ZonedDateTimeComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
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

    @Autowired
    private IReservationEntityRepository entityRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IReservationPeriodOwnerService ownerService;

    long MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;

    @Override
    public void save(AddReservationPeriodDTO dto) {
        ReservationPeriod newPeriod = new ReservationPeriod();
        newPeriod.setStartDate(dto.getStartDate());
        newPeriod.setEndDate(dto.getEndDate());
        newPeriod.setReservationEntity(setEntity(dto.getEntityId(), dto.getOwner()));
        repository.save(newPeriod);
        modifyPeriods(dto.getEntityId());
    }

    private void modifyPeriods(Integer entityId) {
        List<ReservationPeriod> periodList = repository.findAllPeriods(entityId);
        for (ReservationPeriod p : periodList) {
            for(ReservationPeriod p2 : periodList) {
                if(p.getId() == p2.getId()) continue;
                if( ( p2.getStartDate().before(p.getStartDate()) || p2.getStartDate().equals(p.getStartDate()) )
                        && (p2.getEndDate().after(p.getEndDate()) || p2.getEndDate().equals(p.getEndDate())) ) { //1111111111111111111
                    repository.deletePeriod(p.getId());
                    modifyPeriods(entityId);
                    return;
                } else if( (p2.getStartDate().after(p.getStartDate()) || p2.getStartDate().equals(p.getStartDate()))
                        && (p2.getEndDate().before(p.getEndDate()) || p2.getEndDate().equals(p.getEndDate())) ) { //44444444444444444444
                    repository.deletePeriod(p2.getId());
                    modifyPeriods(entityId);
                    return;
                } else if( (p2.getStartDate().before(p.getStartDate()) || p2.getStartDate().equals(p.getStartDate()))
                        && (p2.getEndDate().after(p.getStartDate()) || p2.getEndDate().equals(p.getStartDate()))
                        && (p2.getEndDate().before(p.getEndDate()) || p2.getEndDate().equals(p.getEndDate())) ){ //33333333333333333
                    ReservationPeriod newPeriod = new ReservationPeriod(p2.getStartDate(), p.getEndDate(), p.getReservationEntity());
                    repository.deletePeriod(p2.getId());
                    repository.deletePeriod(p.getId());
                    repository.save(newPeriod);
                    modifyPeriods(entityId);
                    return;
                } else if ( (p.getStartDate().before(p2.getStartDate()) || p.getStartDate().equals(p2.getStartDate()))
                        && (p.getEndDate().after(p2.getStartDate()) || p.getEndDate().equals(p2.getStartDate()))
                        && (p.getEndDate().before(p2.getEndDate()) || p.getEndDate().equals(p2.getEndDate())) ) { //22222222222222222222222
                    ReservationPeriod newPeriod = new ReservationPeriod(p.getStartDate(), p2.getEndDate(), p.getReservationEntity());
                    repository.deletePeriod(p.getId());
                    repository.deletePeriod(p2.getId());
                    repository.save(newPeriod);
                    modifyPeriods(entityId);
                    return;
                }
            }
        }
    }

    @Override
    public List<GetReservationPeriodDTO> findEntityPeriods(Integer entityId) {
        List<GetReservationPeriodDTO> periods =  new ArrayList<>();
        for (ReservationPeriod period : repository.findAllPeriods(entityId)) {
            periods.add(new GetReservationPeriodDTO(period.getId(), period.getStartDate(), period.getEndDate(), entityId));
        }
        return periods;
    }

    @Override
    public List<ReservationPeriodDTO> findAllPeriods(Integer entityId) {
        List<ReservationPeriodDTO> periods = new ArrayList<>();
        for (ReservationPeriod p : repository.findAllPeriods(entityId)) {
            periods.add(new ReservationPeriodDTO(p.getStartDate(), p.getEndDate(), entityId));
        }
        return periods;
    }

    @Override
    public List<ReservationPeriodDTO> findFreePeriods(Integer entityId) {
        List<ReservationPeriodDTO> allPeriods = findAllPeriods(entityId);
        List<ReservationDTO> allReservations = reservationService.findEntityReservations(entityId);
        List<ReservationPeriodDTO> freePeriods = allPeriods;
        for (ReservationDTO reservation : allReservations) {
            freePeriods = getChangedPeriods(freePeriods, reservation, entityId);
        }
        freePeriods = getFuturePeriods(freePeriods);
        freePeriods = reducePeriods(freePeriods);
        return freePeriods;
    }

    private List<ReservationPeriodDTO> getChangedPeriods(List<ReservationPeriodDTO> periods, ReservationDTO reservation, Integer entityId){
        List<ReservationPeriodDTO> newPeriods = new ArrayList<>();
        for (ReservationPeriodDTO period : periods) {
            if(period.getStartDate().before(reservation.getStartDate()) && period.getEndDate().after(reservation.getEndDate())){
                if(period.getStartDate().before(reservation.getStartDate())){
                    ReservationPeriodDTO newPeriod = new ReservationPeriodDTO(period.getStartDate(), reservation.getStartDate(), entityId);
                    newPeriods.add(newPeriod);
                }
                if(reservation.getEndDate().before(period.getEndDate())){
                    ReservationPeriodDTO newPeriod = new ReservationPeriodDTO(reservation.getEndDate(), period.getEndDate(), entityId);
                    newPeriods.add(newPeriod);
                }
            } else {
                ReservationPeriodDTO newPeriod = new ReservationPeriodDTO(period.getStartDate(), period.getEndDate(), entityId);
                newPeriods.add(newPeriod);
            }
        }
        return newPeriods;
    }

    private List<ReservationPeriodDTO> reducePeriods(List<ReservationPeriodDTO> periods){
        List<ReservationPeriodDTO> reducedPeriods = new ArrayList<>();
        for (ReservationPeriodDTO period : periods) {
            if(Math.abs(period.getStartDate().getTime() - period.getEndDate().getTime()) > MILLIS_PER_DAY){
                reducedPeriods.add(period);
            }
        }
        return reducedPeriods;
    }

    @Override
    public List<ReservationPeriodDTO> getAvailablePeriods(Integer entityId, Date startDate, Date endDate) {
        List<ReservationPeriodDTO> entityPeriods = new ArrayList<>();
        for (ReservationPeriod p : repository.findPeriodsByDate(entityId, startDate, endDate)) {
            entityPeriods.add(new ReservationPeriodDTO(p.getStartDate(), p.getEndDate(), entityId));
        }
        List<ReservationDTO> entityReservations = reservationService.findEntityReservations(entityId);
        List<ReservationPeriodDTO> availablePeriods = entityPeriods;
        for (ReservationDTO r : entityReservations) {
            availablePeriods = getChangedPeriods(availablePeriods, r, entityId);
        }
        return availablePeriods;
    }

    private ReservationEntity setEntity(Integer entityId, Integer ownerId){
        User owner = userRepository.getById(ownerId);
        ReservationEntity entity = entityRepository.findEntityById(entityId);
        entity.setOwner(owner);
        return entity;
    }

    @Override
    public List<ReservationPeriodDTO> findFreePeriodsForShipAndOwner(Integer entityId, Integer ownerId){
        List<ReservationPeriodDTO> shipPeriods = findFreePeriods(entityId);
        List<ReservationPeriodOwnerDTO> ownerPeriods = ownerService.getShipOwnerFreePeriods(ownerId);
        List<ReservationPeriodDTO> freePeriods = new ArrayList<>();
        for (ReservationPeriodDTO period : shipPeriods) {
            for (ReservationPeriodOwnerDTO ownerPeriod : ownerPeriods) {
                if(Math.abs(period.getStartDate().getTime() - period.getEndDate().getTime()) > MILLIS_PER_DAY){
                    if(ownerPeriod.getStartDate().before(period.getStartDate()) && ownerPeriod.getEndDate().after(period.getEndDate())){
                        freePeriods.add(period);
                        break;
                    }
                    else if(ownerPeriod.getStartDate().getDay() == period.getStartDate().getDay() &&
                            ownerPeriod.getStartDate().getMonth() == period.getStartDate().getMonth() &&
                            ownerPeriod.getStartDate().getYear() == period.getStartDate().getYear() &&
                            ownerPeriod.getEndDate().after(period.getEndDate())){
                        ReservationPeriodDTO dto;
                        if(ownerPeriod.getStartDate().getTime() > period.getStartDate().getTime()){
                            dto = new ReservationPeriodDTO(period.getStartDate(), period.getEndDate(), entityId);
                        } else {
                            dto = new ReservationPeriodDTO(ownerPeriod.getStartDate(), period.getEndDate(), entityId);
                        }
                        freePeriods.add(dto);
                        break;
                    }
                    else if(ownerPeriod.getEndDate().getDay() == period.getEndDate().getDay() &&
                            ownerPeriod.getEndDate().getMonth() == period.getEndDate().getMonth() &&
                            ownerPeriod.getEndDate().getYear() == period.getEndDate().getYear() &&
                            ownerPeriod.getStartDate().before(period.getStartDate())){
                        ReservationPeriodDTO dto;
                        if(ownerPeriod.getEndDate().getTime() > period.getEndDate().getTime()){
                            dto = new ReservationPeriodDTO(period.getStartDate(), period.getEndDate(), entityId);
                        } else {
                            dto = new ReservationPeriodDTO(period.getStartDate(), ownerPeriod.getEndDate(), entityId);
                        }
                        freePeriods.add(dto);
                        break;
                    } else if(ownerPeriod.getStartDate().getDay() == period.getStartDate().getDay() &&
                            ownerPeriod.getStartDate().getMonth() == period.getStartDate().getMonth() &&
                            ownerPeriod.getStartDate().getYear() == period.getStartDate().getYear() &&
                            ownerPeriod.getEndDate().getDay() == period.getEndDate().getDay() &&
                            ownerPeriod.getEndDate().getMonth() == period.getEndDate().getMonth() &&
                            ownerPeriod.getEndDate().getYear() == period.getEndDate().getYear()){
                        ReservationPeriodDTO dto = new ReservationPeriodDTO();
                        dto.setEntityId(entityId);
                        if(ownerPeriod.getStartDate().getTime() > period.getStartDate().getTime()){
                            dto.setStartDate(period.getStartDate());
                        } else {
                            dto.setStartDate(ownerPeriod.getStartDate());
                        }
                        if(ownerPeriod.getEndDate().getTime() > period.getEndDate().getTime()){
                            dto.setEndDate(period.getEndDate());
                        } else {
                            dto.setEndDate(ownerPeriod.getEndDate());
                        }
                        freePeriods.add(dto);
                        break;
                    }
                }
            }
        }
        freePeriods = getFuturePeriods(freePeriods);
        return  freePeriods;
    }

    private List<ReservationPeriodDTO> getFuturePeriods(List<ReservationPeriodDTO> periods){
        List<ReservationPeriodDTO> futurePeriods = new ArrayList<>();
        for (ReservationPeriodDTO period : periods) {
            if(period.getEndDate().compareTo(new Date()) >= 0){
                if(period.getStartDate().compareTo(new Date()) < 0){
                    futurePeriods.add(new ReservationPeriodDTO(new Date(), period.getEndDate(), period.getEntityId()));
                } else {
                    futurePeriods.add(period);
                }
            }
        }
        return futurePeriods;
    }

    @Override
    public void deletePeriod(Integer entityId, Integer periodId){
        repository.deletePeriod(periodId);
        modifyPeriods(entityId);
    }
}
