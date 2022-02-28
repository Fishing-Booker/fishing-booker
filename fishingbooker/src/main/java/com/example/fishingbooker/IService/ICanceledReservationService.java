package com.example.fishingbooker.IService;

import com.example.fishingbooker.Model.CanceledReservation;

import java.util.Date;

public interface ICanceledReservationService {
    void save(CanceledReservation canceledReservation);
    boolean doesCanceledReservationExist(Integer clientId, Integer entityId, Date startDate, Date endDate);
}
