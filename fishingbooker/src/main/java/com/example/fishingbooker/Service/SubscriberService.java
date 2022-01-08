package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.SubscriberDTO;
import com.example.fishingbooker.DTO.SubscriptionDTO;
import com.example.fishingbooker.IRepository.ISubscriberRepository;
import com.example.fishingbooker.IService.IReservationEntityService;
import com.example.fishingbooker.IService.ISubscriberService;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Mapper.SubscriptionMapper;
import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.Model.Subscriber;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriberService implements ISubscriberService {

    @Autowired
    private ISubscriberRepository subscriberRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IReservationEntityService reservationEntityService;

    @Override
    public void addSubscriber(SubscriberDTO dto) {
        User user = userService.findUserById(dto.getUserId());
        ReservationEntity entity = reservationEntityService.getEntityById(dto.getEntityId());
        Subscriber subscriber = new Subscriber(user, entity);
        subscriberRepository.save(subscriber);
    }

    @Override
    public Boolean isSubscribed(SubscriberDTO dto) {
        return subscriberRepository.getSubscriber(dto.getEntityId(), dto.getUserId()) != null;
    }

    @Override
    public Boolean unsubscribe(Integer entityId, Integer userId) {
        if (subscriberRepository.getSubscriber(entityId, userId) != null) {
            subscriberRepository.delete(subscriberRepository.getSubscriber(entityId, userId));
            return true;
        } else return false;
    }

    @Override
    public List<SubscriptionDTO> getSubscriptions(Integer id) {
        List<ReservationEntity> entities = subscriberRepository.getSubscriptions(id);
        List<SubscriptionDTO> subscriptions = new ArrayList<>();
        for (ReservationEntity entity : entities) {
            subscriptions.add(SubscriptionMapper.mapToDTO(entity));
        }
        return subscriptions;
    }
}