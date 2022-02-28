package com.example.fishingbooker.Service;

import com.example.fishingbooker.IRepository.ICanceledReservationRepository;
import com.example.fishingbooker.IService.ICanceledReservationService;
import com.example.fishingbooker.Model.CanceledReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CanceledReservationService implements ICanceledReservationService {

    @Autowired
    ICanceledReservationRepository canceledReservationRepository;

    @Override
    public void save(CanceledReservation canceledReservation) {
        canceledReservationRepository.save(canceledReservation);
    }

    @Override
    public boolean doesCanceledReservationExist(Integer clientId, Integer entityId, Date startDate, Date endDate) {
        return canceledReservationRepository.findCanceledReservation(clientId, entityId, startDate, endDate) != null;
    }
}
