package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.PasswordDTO;
import com.example.fishingbooker.IService.IRoleService;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static java.util.Arrays.stream;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

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

    @GetMapping("/getRole/{id}")
    @PreAuthorize("isAuthenticated()")
    public String getUserRole(@PathVariable Integer id) { return this.userService.findUserRolename(id); }

    @GetMapping("/getLoggedUser")
    public User getLoggedUser(Principal user) {
        return this.userService.findByUsername(user.getName());
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody PasswordDTO passwordDTO) {
        userService.changePassword(passwordDTO.getPassword(), passwordDTO.getId());
        return ResponseEntity.ok("Password is successfully changed!");
    }

}
