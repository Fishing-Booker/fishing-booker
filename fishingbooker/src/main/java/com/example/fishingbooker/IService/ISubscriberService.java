package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.SubscriberDTO;
import com.example.fishingbooker.DTO.SubscriptionDTO;
import com.example.fishingbooker.DTO.reservationAction.AddReservationActionDTO;

import java.util.List;

public interface ISubscriberService {
    void addSubscriber(SubscriberDTO dto);

    Boolean isSubscribed(SubscriberDTO dto);

    Boolean unsubscribe(Integer entityId, Integer userId);

    List<SubscriptionDTO> getSubscriptions(Integer id);

    void sendEmailWithActionInfo(AddReservationActionDTO action);
    void sendEmailWithActionReservationInfo(Integer clientId);
}
