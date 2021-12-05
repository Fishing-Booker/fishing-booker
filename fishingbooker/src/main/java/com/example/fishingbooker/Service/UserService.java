package com.example.fishingbooker.Service;

import com.example.fishingbooker.Model.Role;
import com.example.fishingbooker.Model.User;
import com.example.fishingbooker.Repository.RoleRepository;
import com.example.fishingbooker.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
