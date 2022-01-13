package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.reservationPeriodOwner.ReservationPeriodOwnerDTO;
import com.example.fishingbooker.IRepository.IReservationPeriodOwnerRepository;
import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.IService.IReservationPeriodOwnerService;
import com.example.fishingbooker.Model.ReservationPeriodOwner;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReservationPeriodOwnerService implements IReservationPeriodOwnerService {

    @Autowired
    private IReservationPeriodOwnerRepository reservationPeriodOwnerRepository;

    @Autowired
    private IUserRepository userRepository;

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


}
