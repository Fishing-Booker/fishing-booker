package com.example.fishingbooker.service;

import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.Model.User;
import com.example.fishingbooker.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private User userMock;

    @InjectMocks
    private UserService userService;

    @Test
    public void getUserByIdTest() {
        when(userRepository.getById(7)).thenReturn(userMock);

        User user = userService.findUserById(7);

        Assertions.assertEquals(user, userMock);
    }

    @Test
    public void findAllTest() {
        List<User> users = new ArrayList<>();
        users.add(userMock);

        when(userRepository.findAll()).thenReturn(users);

        List<User> expectedUsers = userService.findAll();

        Assertions.assertEquals(1, expectedUsers.size());
    }

    @Test
    public void doesUserExistTest() {
        userRepository = mock(IUserRepository.class);
        userService = new UserService(userRepository);

        when(userRepository.getById(7)).thenReturn(userMock);

        User doesExist = userService.doesExist(7);

        Assertions.assertEquals(doesExist, userMock);
        //assertTrue(userMock.equals(doesExist));
    }
}
