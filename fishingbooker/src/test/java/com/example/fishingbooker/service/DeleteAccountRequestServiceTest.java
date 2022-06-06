package com.example.fishingbooker.service;

import com.example.fishingbooker.DTO.ResponseDTO;
import com.example.fishingbooker.IRepository.IDeleteAccountRequestRepository;
import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.Model.DeleteAccountRequest;
import com.example.fishingbooker.Model.Role;
import com.example.fishingbooker.Model.User;
import com.example.fishingbooker.Service.DeleteAccountRequestService;
import com.example.fishingbooker.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeleteAccountRequestServiceTest {

    /*@Mock
    private IDeleteAccountRequestRepository deleteAccountRequestRepositoryMock;

    @Mock
    private IUserRepository userRepositoryMock;

    @Mock
    private UserService userServiceMock;

    @InjectMocks
    private DeleteAccountRequestService deleteAccountRequestService;

    @Test
    public void approveDeleteRequestTest(){
        User user = new User(1, "user", "user123",
                "User", "User", "user@gmail.com", "address 123",
                "city", "county", "phone",
                Arrays.asList(new Role(1, "CLIENT")));

        DeleteAccountRequest deleteAccountRequest = new DeleteAccountRequest(1, user.getUsername(), "", false, false);
        ResponseDTO dto = new ResponseDTO(1, deleteAccountRequest.getUserId(), deleteAccountRequest.getDeleteReason());

        when(userRepositoryMock.findByUsername(user.getUsername())).thenReturn(user);
        doNothing().when(userRepositoryMock).deleteByUsername(user.getUsername());
        doNothing().when(userServiceMock).sendEmailResponseDeleteReq(user,"");
        doNothing().when(deleteAccountRequestRepositoryMock).approve(deleteAccountRequest.getId());
        when(deleteAccountRequestRepositoryMock.findById(deleteAccountRequest.getId())).thenReturn(java.util.Optional.of(new DeleteAccountRequest(1, user.getUsername(), "", true, false)));

        DeleteAccountRequest deleteAccountRequest1 = deleteAccountRequestService.approveDeleteRequest(dto);

        assertThat(deleteAccountRequest1.getAccepted()).isTrue();
    }

    @Test
    public void rejectDeleteRequestTest(){
        User user = new User(1, "user", "user123",
                "User", "User", "user@gmail.com", "address 123",
                "city", "county", "phone",
                Arrays.asList(new Role(1, "CLIENT")));

        DeleteAccountRequest deleteAccountRequest = new DeleteAccountRequest(1, user.getUsername(), "", false, false);
        ResponseDTO dto = new ResponseDTO(1, deleteAccountRequest.getUserId(), deleteAccountRequest.getDeleteReason());

        when(userRepositoryMock.findByUsername(user.getUsername())).thenReturn(user);
        doNothing().when(userServiceMock).sendEmailResponseDeleteReq(user,"");
        doNothing().when(deleteAccountRequestRepositoryMock).disapprove(deleteAccountRequest.getId());
        when(deleteAccountRequestRepositoryMock.findById(deleteAccountRequest.getId())).thenReturn(java.util.Optional.of(new DeleteAccountRequest(1, user.getUsername(), "", false, true)));

        DeleteAccountRequest deleteAccountRequest1 = deleteAccountRequestService.rejectDeleteRequest(dto);

        assertThat(deleteAccountRequest1.getDisapproved()).isTrue();
    }

    @Test
    public void findAllTest(){
        User user = new User(1, "user", "user123",
                "User", "User", "user@gmail.com", "address 123",
                "city", "county", "phone",
                Arrays.asList(new Role(1, "CLIENT")));

        DeleteAccountRequest deleteAccountRequest = new DeleteAccountRequest(1, user.getUsername(), "", false, false);

        when(deleteAccountRequestRepositoryMock.findAll()).thenReturn(Arrays.asList(deleteAccountRequest));

        List<DeleteAccountRequest> requests = deleteAccountRequestService.findAll();

        assertThat(requests).hasSize(1);
    }*/

}
