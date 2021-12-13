package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.UserDTO;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.IService.UserService;
import com.example.fishingbooker.Model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.stream;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/user/{username}")
    @PreAuthorize("isAuthenticated()")
    public User loadByUsername(@PathVariable String username) {
        return this.userService.findByUsername(username);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers(){
        return this.userService.findAll();
    }

}
