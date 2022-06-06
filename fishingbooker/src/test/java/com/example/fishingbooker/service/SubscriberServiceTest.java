//package com.example.fishingbooker.service;
//
//import com.example.fishingbooker.DTO.SubscriberDTO;
//import com.example.fishingbooker.IRepository.ISubscriberRepository;
//import com.example.fishingbooker.Model.*;
//import com.example.fishingbooker.Service.SubscriberService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//public class SubscriberServiceTest {
//
//    @Mock
//    private ISubscriberRepository subscriberRepository;
//
//    @InjectMocks
//    private SubscriberService subscriberService;
//
//    @Test
//    public void isSubscribedTest() {
//        User client = new User(1, "user", "user123",
//                "User", "User", "user@gmail.com", "address 123",
//                "city", "county", "phone",
//                Arrays.asList(new Role(1, "CLIENT")));
//
//        Adventure adventure = new Adventure(1, new User(), "Adventure",
//                new Location(1, 253.5, 12.0, "address", "city", "country"),
//                "description", "rules", 2, 4.5, "biography",
//                10, new ArrayList<Image>(), "fishingEquipment");
//
//        Subscriber subscriber = new Subscriber(1, client, adventure);
//
//        when(subscriberRepository.save(subscriber)).thenReturn(subscriber);
//        when(subscriberRepository.getSubscriber(subscriber.getReservationEntity().getId(), subscriber.getClient().getId())).thenReturn(subscriber);
//
//        SubscriberDTO subscriberDTO = new SubscriberDTO(subscriber.getReservationEntity().getId(), subscriber.getClient().getId());
//
//        assertThat(subscriberService.isSubscribed(subscriberDTO)).isTrue();
//
//    }
//
//    @Test
//    public void usubscribeTest() {
//        User client = new User(1, "user", "user123",
//                "User", "User", "user@gmail.com", "address 123",
//                "city", "county", "phone",
//                Arrays.asList(new Role(1, "CLIENT")));
//
//        Adventure adventure = new Adventure(1, new User(), "Adventure",
//                new Location(1, 253.5, 12.0, "address", "city", "country"),
//                "description", "rules", 2, 4.5, "biography",
//                10, new ArrayList<Image>(), "fishingEquipment");
//
//        Subscriber subscriber = new Subscriber(1, client, adventure);
//
//        when(subscriberRepository.save(subscriber)).thenReturn(subscriber);
//        when(subscriberRepository.getSubscriber(subscriber.getReservationEntity().getId(), subscriber.getClient().getId())).thenReturn(subscriber);
//        doNothing().when(subscriberRepository).delete(subscriber);
//
//        assertThat(subscriberService.unsubscribe(subscriber.getReservationEntity().getId(), subscriber.getClient().getId())).isTrue();
//    }
//}
