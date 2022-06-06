package com.example.fishingbooker.service;

import com.example.fishingbooker.IRepository.IPenaltyRepository;
import com.example.fishingbooker.Model.Penalty;
import com.example.fishingbooker.Model.Role;
import com.example.fishingbooker.Model.User;
import com.example.fishingbooker.Service.PenaltyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PenaltyServiceTest {

   /* @Mock
    private IPenaltyRepository penaltyRepository;

    @InjectMocks
    private PenaltyService penaltyService;

    @Test
    public void getClientPenaltiesTest() {
        User client1 = new User(1, "petar", "petar123",
                "Petar", "Petrovic", "petar@gmail.com", "Bulvear oslobodjenja 123",
                "Novi Sad", "Srbija", "0612345678",
                Arrays.asList(new Role(1, "CLIENT")));

        User client2 = new User(2, "mika", "mika123",
                "Mika", "Mikic", "mika@gmail.com", "Bulvear Evrope 123",
                "Novi Sad", "Srbija", "0612345678",
                Arrays.asList(new Role(1, "CLIENT")));

        Penalty penalty1 = new Penalty(1, client1, 3);
        Penalty penalty2 = new Penalty(2, client2, 2);

        when(penaltyRepository.findAll()).thenReturn(Arrays.asList(penalty1, penalty2));
        when(penaltyRepository.findClientPenalties(2)).thenReturn(penalty2);

        assertThat(penaltyService.findClientPenalties(client2.getId())).isEqualTo(penalty2.getPenalties());
    }*/
}
