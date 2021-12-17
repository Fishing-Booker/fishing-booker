package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.DTO.UserDTO;
import com.example.fishingbooker.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    Optional<User> findById(Integer id);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    List<User> findAll();
    User save(User user);

    @Query("UPDATE User u SET u.isApproved = true WHERE u.id = ?1")
    @Modifying
    @Transactional
    void enable(Integer id);

    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    User findByVerificationCode(String code);

    @Query("SELECT u FROM User u WHERE u.isApproved = false")
    List<User> findUnapprovedUsers();

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User findUserById(Integer id);
}
