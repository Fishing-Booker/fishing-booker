package com.example.fishingbooker.service;

import com.example.fishingbooker.Enum.ReservationType;
import com.example.fishingbooker.IRepository.IReservationRepository;
import com.example.fishingbooker.Model.*;
import com.example.fishingbooker.Service.ReservationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ReservationServiceTest {

    @Mock
    private IReservationRepository reservationRepository;

    @Mock
    private Reservation reservationMock;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    public void findAllTest() {
        when(reservationRepository.findAll()).thenReturn(Arrays.asList(reservationMock));

        List<Reservation> reservations = reservationService.findAll();

        assertThat(reservations).hasSize(1);
    }

    @Test
    public void cancelReservationTest() {
        User client = new User(1, "user", "user123",
                "User", "User", "user@gmail.com", "address 123",
                "city", "county", "phone",
                Arrays.asList(new Role(1, "CLIENT")));

        User owner = new User(2, "user", "user123",
                "User", "User", "user@gmail.com", "address 123",
                "city", "county", "phone",
                Arrays.asList(new Role(1, "INSTRUCTOR")));

        Adventure adventure1 = new Adventure(1, owner, "Adventure",
                new Location(1, 253.5, 12.0, "address", "city", "country"),
                "description", "rules", 2, 4.5, "biography",
                10, new ArrayList<Image>(), "fishingEquipment");

        Adventure adventure2 = new Adventure(2, owner, "Adventure",
                new Location(1, 253.5, 12.0, "address", "city", "country"),
                "description", "rules", 2, 4.5, "biography",
                10, new ArrayList<Image>(), "fishingEquipment");

        Reservation reservation1 = new Reservation(1, new Date(), new Date(), adventure1, client, ReservationType.quickReservation, 200.0, 5,
                "none", "wifi", true);

        Reservation reservation2 = new Reservation(2, new Date(), new Date(), adventure2, client, ReservationType.quickReservation, 250.0, 7,
                "none", "wifi, parking", true);

        when(reservationRepository.findAll()).thenReturn(Arrays.asList(reservation1, reservation2));
        doNothing().when(reservationRepository).cancelReservation(2);

        Integer reservationsBeforeCanceling = reservationService.findAll().size();
        reservationService.cancelRegularReservation(2);

        when(reservationRepository.findAll()).thenReturn(Arrays.asList(reservation1));

        List<Reservation> reservations = reservationService.findAll();
        assertThat(reservations).hasSize(reservationsBeforeCanceling - 1);
    }

}
