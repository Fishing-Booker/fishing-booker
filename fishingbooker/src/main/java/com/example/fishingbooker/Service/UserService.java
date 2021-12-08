package com.example.fishingbooker.Service;

import com.example.fishingbooker.Model.User;
import com.example.fishingbooker.IRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
