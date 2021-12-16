package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.UserDTO;
import com.example.fishingbooker.Model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> findById(Integer id);
    User findByUsername(String username);
    List<User> findAll();
    User save(UserDTO userDTO);
    void sendVerificationEmail(User user);
    boolean verify(String verificationCode);
    String findUserRolename(Integer id);
    List<User> findUnapprovedUsers();
    void sendVerificationEmailToOwnersAndInstructors(User user);
    void sendRejectingEmail(User user);
    void changePassword(String password, Integer id);
    User update(UserDTO userDTO, Integer id);
}
