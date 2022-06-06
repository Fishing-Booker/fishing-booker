package com.example.fishingbooker.service;

import com.example.fishingbooker.DTO.UserDTO;
import com.example.fishingbooker.IRepository.IAccountRequestRepository;
import com.example.fishingbooker.Model.AccountRequest;
import com.example.fishingbooker.Model.User;
import com.example.fishingbooker.Service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import static com.example.fishingbooker.constants.AccountRequestConstants.*;
import static com.example.fishingbooker.constants.UserConstants.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AccountServiceTest {
    /*@Mock
    private IAccountRequestRepository requestRepositoryMock;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void testFindAll(){
        when(requestRepositoryMock.findAll()).thenReturn(Arrays.asList(new AccountRequest(ACC_ID, USER_ID, REG_REASON)));

        List<AccountRequest> requests = accountService.findAll();

        assertThat(requests).hasSize(1);

        verify(requestRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(requestRepositoryMock);

    }*/
}
