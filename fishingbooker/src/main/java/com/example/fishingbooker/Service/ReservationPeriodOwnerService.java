package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.reservation.OwnerReservationDTO;
import com.example.fishingbooker.DTO.reservation.ReservationDTO;
import com.example.fishingbooker.DTO.reservationPeriod.ReservationPeriodDTO;
import com.example.fishingbooker.DTO.reservationPeriodOwner.ReservationPeriodOwnerDTO;
import com.example.fishingbooker.IRepository.IReservationPeriodOwnerRepository;
import com.example.fishingbooker.IRepository.IShipOwnerReservationRepository;
import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.IService.IReservationEntityService;
import com.example.fishingbooker.IService.IReservationPeriodOwnerService;
import com.example.fishingbooker.IService.IReservationService;
import com.example.fishingbooker.Model.Reservation;
import com.example.fishingbooker.Model.ReservationPeriodOwner;
import com.example.fishingbooker.Model.ShipOwnerReservation;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ReservationPeriodOwnerService implements IReservationPeriodOwnerService {

    @Autowired
    private IReservationPeriodOwnerRepository reservationPeriodOwnerRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IReservationEntityService entityService;

    @Autowired
    private IReservationService reservationService;

    @Autowired
    private IShipOwnerReservationRepository ownerRepository;

    @Override
    public void save(ReservationPeriodOwnerDTO dto) {
        User u = userRepository.getById(dto.getOwner());
        ReservationPeriodOwner newPeriod = new ReservationPeriodOwner(dto.getStartDate(), dto.getEndDate(), u);
        reservationPeriodOwnerRepository.save(newPeriod);
        modifyPeriods(u.getId());
    }

    @Override
    public List<ReservationPeriodOwnerDTO> findAllPeriods(Integer ownerId) {
        List<ReservationPeriodOwnerDTO> periods = new ArrayList<>();
        for(ReservationPeriodOwner p : reservationPeriodOwnerRepository.findAllOwnerPeriods(ownerId)) {
            periods.add(new ReservationPeriodOwnerDTO(p.getOwner().getId(), p.getStartDate(), p.getEndDate()));
        }
        return periods;
    }

    @Override
    public List<ReservationPeriodDTO> getAvailablePeriods(Integer entityId, Date startDate, Date endDate) {
        List<ReservationPeriodDTO> entityPeriods = new ArrayList<>();
        Integer ownerId = entityService.getOwnerId(entityId);
        for (ReservationPeriodOwner p : reservationPeriodOwnerRepository.findPeriodsByDate(ownerId, startDate, endDate)) {
            entityPeriods.add(new ReservationPeriodDTO(p.getStartDate(), p.getEndDate(), entityId));
        }
        List<ReservationDTO> entityReservations = reservationService.findEntityReservations(entityId);
        List<ReservationPeriodDTO> availablePeriods = entityPeriods;
        for (ReservationDTO r : entityReservations) {
            availablePeriods = getChangedPeriods(availablePeriods, r, entityId);
        }
        return availablePeriods;
    }

    private void modifyPeriods(Integer ownerId) {
        List<ReservationPeriodOwner> periodOwnerList = reservationPeriodOwnerRepository.findAllOwnerPeriods(ownerId);
        for (ReservationPeriodOwner p : periodOwnerList) {
            for(ReservationPeriodOwner p2 : periodOwnerList) {
                if(p.getId() == p2.getId()) continue;
                if( ( p2.getStartDate().before(p.getStartDate()) || p2.getStartDate().equals(p.getStartDate()) )
                        && (p2.getEndDate().after(p.getEndDate()) || p2.getEndDate().equals(p.getEndDate())) ) { //1111111111111111111
                    reservationPeriodOwnerRepository.deletePeriod(p.getId());
                    modifyPeriods(ownerId);
                    return;
                } else if( (p2.getStartDate().after(p.getStartDate()) || p2.getStartDate().equals(p.getStartDate()))
                        && (p2.getEndDate().before(p.getEndDate()) || p2.getEndDate().equals(p.getEndDate())) ) { //44444444444444444444
                    reservationPeriodOwnerRepository.deletePeriod(p2.getId());
                    modifyPeriods(ownerId);
                    return;
                } else if( (p2.getStartDate().before(p.getStartDate()) || p2.getStartDate().equals(p.getStartDate()))
                        && (p2.getEndDate().after(p.getStartDate()) || p2.getEndDate().equals(p.getStartDate()))
                        && (p2.getEndDate().before(p.getEndDate()) || p2.getEndDate().equals(p.getEndDate())) ){ //33333333333333333
                    ReservationPeriodOwner newPeriod = new ReservationPeriodOwner(p2.getStartDate(), p.getEndDate(), p.getOwner());
                    reservationPeriodOwnerRepository.deletePeriod(p2.getId());
                    reservationPeriodOwnerRepository.deletePeriod(p.getId());
                    reservationPeriodOwnerRepository.save(newPeriod);
                    modifyPeriods(ownerId);
                    return;
                } else if ( (p.getStartDate().before(p2.getStartDate()) || p.getStartDate().equals(p2.getStartDate()))
                        && (p.getEndDate().after(p2.getStartDate()) || p.getEndDate().equals(p2.getStartDate()))
                        && (p.getEndDate().before(p2.getEndDate()) || p.getEndDate().equals(p2.getEndDate())) ) { //22222222222222222222222
                    ReservationPeriodOwner newPeriod = new ReservationPeriodOwner(p.getStartDate(), p2.getEndDate(), p.getOwner());
                    reservationPeriodOwnerRepository.deletePeriod(p.getId());
                    reservationPeriodOwnerRepository.deletePeriod(p2.getId());
                    reservationPeriodOwnerRepository.save(newPeriod);
                    modifyPeriods(ownerId);
                    return;
                }
            }
        }
    }

    private List<ReservationPeriodDTO> getChangedPeriods(List<ReservationPeriodDTO> periods, ReservationDTO reservation, Integer entityId) {
        List<ReservationPeriodDTO> newPeriods = new ArrayList<>();
        for (ReservationPeriodDTO period : periods) {
            if (period.getStartDate().before(reservation.getStartDate()) && period.getEndDate().after(reservation.getEndDate())) {
                if (period.getStartDate().before(reservation.getStartDate())) {
                    ReservationPeriodDTO newPeriod = new ReservationPeriodDTO(period.getStartDate(), reservation.getStartDate(), entityId); // owner id
                    newPeriods.add(newPeriod);
                }
                if (reservation.getEndDate().before(period.getEndDate())) {
                    ReservationPeriodDTO newPeriod = new ReservationPeriodDTO(reservation.getEndDate(), period.getEndDate(), entityId); // owner id
                    newPeriods.add(newPeriod);
                }
            } else {
                ReservationPeriodDTO newPeriod = new ReservationPeriodDTO(period.getStartDate(), period.getEndDate(), entityId); // owner id
                newPeriods.add(newPeriod);
            }
        }
        return  newPeriods;
    }

    public List<ReservationPeriodDTO> getPeriodsWhenOwnerIsFree(List<ReservationPeriodDTO> periods){
        List<ShipOwnerReservation> ownersReservations = ownerRepository.findOwnerReservations(1);
        return null;
    }

    @Override
    public List<ReservationPeriodOwnerDTO> getShipOwnerFreePeriods(Integer ownerId){
        List<ShipOwnerReservation> ownersReservations = ownerRepository.findOwnerReservations(ownerId);
        List<ReservationPeriodOwnerDTO> freePeriods = findAllPeriods(ownerId);
        for (ShipOwnerReservation r : ownersReservations) {
            ReservationDTO dto = new ReservationDTO(r.getReservation().getId(), r.getReservation().getStartDate(),
                    r.getReservation().getEndDate(), r.getReservation().getClient().getUsername(), r.getReservation().getReservationEntity().getId(),
                    r.getReservation().getReservationEntity().getName());
            freePeriods = getChangedOwnerPeriods(freePeriods, dto, ownerId);
        }
        return freePeriods;
    }

    private List<ReservationPeriodOwnerDTO> getChangedOwnerPeriods(List<ReservationPeriodOwnerDTO> periods, ReservationDTO reservation, Integer ownerId) {
        List<ReservationPeriodOwnerDTO> newPeriods = new ArrayList<>();
        for (ReservationPeriodOwnerDTO period : periods) {
            if (period.getStartDate().before(reservation.getStartDate()) && period.getEndDate().after(reservation.getEndDate())) {
                if (period.getStartDate().before(reservation.getStartDate())) {
                    ReservationPeriodOwnerDTO newPeriod = new ReservationPeriodOwnerDTO(ownerId, period.getStartDate(), reservation.getStartDate());
                    newPeriods.add(newPeriod);
                }
                if (reservation.getEndDate().before(period.getEndDate())) {
                    ReservationPeriodOwnerDTO newPeriod = new ReservationPeriodOwnerDTO(ownerId, reservation.getEndDate(), period.getEndDate());
                    newPeriods.add(newPeriod);
                }
            } else {
                ReservationPeriodOwnerDTO newPeriod = new ReservationPeriodOwnerDTO(ownerId, period.getStartDate(), period.getEndDate());
                newPeriods.add(newPeriod);
            }
        }
        return  newPeriods;
    }

    @Override
    public void addShipOwnerReservation(Reservation reservation, Integer ownerId){
        User owner = userRepository.getById(ownerId);
        ShipOwnerReservation newReservation = new ShipOwnerReservation();
        newReservation.setReservation(reservation);
        newReservation.setOwner(owner);
        ownerRepository.save(newReservation);
    }

}
