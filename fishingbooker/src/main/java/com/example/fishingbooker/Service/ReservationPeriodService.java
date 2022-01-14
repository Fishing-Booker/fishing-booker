package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.reservation.ReservationDTO;
import com.example.fishingbooker.DTO.reservationPeriod.AddReservationPeriodDTO;
import com.example.fishingbooker.DTO.reservationPeriod.ReservationPeriodDTO;
import com.example.fishingbooker.IRepository.IReservationEntityRepository;
import com.example.fishingbooker.IRepository.IReservationPeriodRepository;
import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.IService.*;
import com.example.fishingbooker.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
}
