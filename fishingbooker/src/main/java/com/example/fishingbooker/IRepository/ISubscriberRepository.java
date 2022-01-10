package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.Model.Subscriber;
import com.example.fishingbooker.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISubscriberRepository extends JpaRepository<Subscriber, Integer> {

    @Query("SELECT s FROM Subscriber s WHERE s.reservationEntity.id=?1 AND s.client.id=?2")
    Subscriber getSubscriber(Integer entityId, Integer userId);

    @Query("SELECT s.reservationEntity FROM Subscriber s WHERE s.client.id=?1")
    List<ReservationEntity> getSubscriptions(Integer id);

    @Query("SELECT s.client FROM Subscriber s WHERE s.reservationEntity.id=?1")
    List<User> getSubscribers(Integer entityId);

}
