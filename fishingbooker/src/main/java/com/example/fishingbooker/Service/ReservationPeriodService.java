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
            freePeriods = getChangedPeriods(freePeriods, reservation);
        }
        return freePeriods;
    }

    private List<ReservationPeriodDTO> getChangedPeriods(List<ReservationPeriodDTO> periods, ReservationDTO reservation){
        List<ReservationPeriodDTO> newPeriods = new ArrayList<>();
        for (ReservationPeriodDTO period : periods) {
            if(period.getStartDate().before(reservation.getStartDate()) && period.getEndDate().after(reservation.getEndDate())){
                if(period.getStartDate().before(reservation.getStartDate())){
                    ReservationPeriodDTO newPeriod = new ReservationPeriodDTO(period.getStartDate(), reservation.getStartDate(), 1);
                    newPeriods.add(newPeriod);
                }
                if(reservation.getEndDate().before(period.getEndDate())){
                    ReservationPeriodDTO newPeriod = new ReservationPeriodDTO(reservation.getEndDate(), period.getEndDate(), 1);
                    newPeriods.add(newPeriod);
                }
            } else {
                ReservationPeriodDTO newPeriod = new ReservationPeriodDTO(period.getStartDate(), period.getEndDate(), 1);
                newPeriods.add(newPeriod);
            }
        }
        return newPeriods;
    }

    private ReservationEntity setEntity(Integer entityId, Integer ownerId){
        User owner = userRepository.getById(ownerId);
        ReservationEntity entity = entityRepository.findEntityById(entityId);
        entity.setOwner(owner);
        return entity;
    }
}
