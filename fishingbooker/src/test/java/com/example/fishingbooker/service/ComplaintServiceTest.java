package com.example.fishingbooker.service;

import com.example.fishingbooker.DTO.ComplaintDTO;
import com.example.fishingbooker.IRepository.IComplaintRepository;
import com.example.fishingbooker.Mapper.ComplaintMapper;
import com.example.fishingbooker.Model.*;
import com.example.fishingbooker.Service.ComplaintService;
import com.example.fishingbooker.Service.ReservationEntityService;
import com.example.fishingbooker.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ComplaintServiceTest {

    @Mock
    private IComplaintRepository complaintRepositoryMock;

    @Mock
    private UserService userServiceMock;

    @Mock
    private ReservationEntityService reservationEntityServiceMock;

    @InjectMocks
    private ComplaintService complaintService;

    @Test
    public void addComplaintTest(){
        ComplaintDTO complaintDTO = new ComplaintDTO(1, 1, "");
        User user = new User(1, "user", "user123",
                "User", "User", "user@gmail.com", "address 123",
                "city", "county", "phone",
                Arrays.asList(new Role(1, "CLIENT")));

        ReservationEntity adventure = new ReservationEntity(1, user, "Adventure",
                new Location(1, 253.5, 12.0, "address", "city", "country"),
                "description", "rules", 2, 4.5,
                10);

        Complaint complaint = new Complaint(1, complaintDTO.getText(), adventure, user, false);

        when(userServiceMock.findUserById(complaintDTO.getClientId())).thenReturn(user);
        doReturn(complaint).when(complaintRepositoryMock).save(complaint);
        when(reservationEntityServiceMock.getEntityById(complaintDTO.getEntityId())).thenReturn(adventure);

        Complaint c = complaintService.addComplaint(complaintDTO);

        assertThat(c).isNull();
    }

}
