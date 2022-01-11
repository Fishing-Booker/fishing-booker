package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.DeleteAccountRequestDTO;
import com.example.fishingbooker.DTO.EntityDTO;
import com.example.fishingbooker.DTO.PasswordDTO;
import com.example.fishingbooker.DTO.UserDTO;
import com.example.fishingbooker.IService.IDeleteAccountRequestService;
import com.example.fishingbooker.IService.IRoleService;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Model.DeleteAccountRequest;
import com.example.fishingbooker.Model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @Autowired
    private IDeleteAccountRequestService deleteAccountRequestService;

    @GetMapping("/user/{username}")
    @PreAuthorize("isAuthenticated()")
    public User loadByUsername(@PathVariable String username) {
        return this.userService.findByUsername(username);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN') || hasRole('DEFADMIN')")
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

    @PutMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody PasswordDTO passwordDTO) {
        userService.changePassword(passwordDTO.getPassword(), passwordDTO.getId());
        return ResponseEntity.ok("Password is successfully changed!");
    }

    @PutMapping("/user/{id}")
    public User update(@RequestBody UserDTO userDTO, @PathVariable Integer id) {
        return userService.update(userDTO, id);
    }

    @PostMapping("/deleteAccount")
    public DeleteAccountRequest sendRequestForDeletingAccount(@RequestBody DeleteAccountRequestDTO dto) {
        return deleteAccountRequestService.save(dto);
    }

    @GetMapping("/getUser/{id}")
    public UserDTO findUserById(@PathVariable Integer id) {
        return userService.findUserByIdDto(id);
    }

    @GetMapping("/getUserEntities/{id}")
    public List<EntityDTO> findUserEntities(@PathVariable Integer id) throws IOException {
        return userService.findUserEntities(id);
    }

    @GetMapping("/getUserList")
    @PreAuthorize("hasRole('ADMIN') || hasRole('DEFADMIN')")
    public List<UserDTO> getUserList(Principal user) {
        return userService.getUserList(user.getName());
    }

    @PutMapping("/changePasswordAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> firstLoginAdmin(@RequestBody PasswordDTO passwordDTO) {
        userService.adminFirstLogin(passwordDTO.getPassword(), passwordDTO.getId());
        return ResponseEntity.ok("Password is successfully changed!");
    }

    @PutMapping("/deleteUser/{id}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('DEFADMIN')")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/deleteUsersEntity/{entityId}/{userId}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('DEFADMIN')")
    public ResponseEntity<String> deleteUsersEntity(@PathVariable Integer entityId, @PathVariable Integer userId) {
        userService.deleteUserEntity(entityId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
