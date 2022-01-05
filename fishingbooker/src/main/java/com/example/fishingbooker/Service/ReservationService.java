package com.example.fishingbooker.Service;

import com.example.fishingbooker.IRepository.IReservationRepository;
import com.example.fishingbooker.IService.IReservationService;
import com.example.fishingbooker.Model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    private IReservationRepository reservationRepository;

    @Override
    public List<Reservation> findEntityReservations(Integer entityId) {
        return reservationRepository.findEntityReservations(entityId);
    }
}
