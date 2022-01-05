package com.example.fishingbooker.IService;

import com.example.fishingbooker.Model.Reservation;

import java.util.List;

public interface IReservationService {

    List<Reservation> findEntityReservations(Integer entityId);

}
