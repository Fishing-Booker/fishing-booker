package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ISubscriberRepository extends JpaRepository<Subscriber, Integer> {

    @Query("SELECT s FROM Subscriber s WHERE s.reservationEntity.id=?1 AND s.client.id=?2")
    Subscriber getSubscriber(Integer entityId, Integer userId);


}
