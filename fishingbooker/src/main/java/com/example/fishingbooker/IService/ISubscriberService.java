package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.SubscriberDTO;

public interface ISubscriberService {
    void addSubscriber(SubscriberDTO dto);

    Boolean isSubscribed(SubscriberDTO dto);

    Boolean unsubscribe(Integer entityId, Integer userId);
}
