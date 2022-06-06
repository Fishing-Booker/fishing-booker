package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.DTO.UserDTO;
import com.example.fishingbooker.Model.User;
import org.springframework.data.jpa.repository.*;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.username=?1")
    User findByUsername(String username);

    Optional<User> findById(Integer id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("select u from User u where u.isDeleted=false")
    List<User> findAll();

    User save(User user);

    @Query("UPDATE User u SET u.isApproved = true WHERE u.id = ?1")
    @Modifying
    @Transactional
    void approve(Integer id);

    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    User findByVerificationCode(String code);

    @Query("SELECT u FROM User u WHERE u.isApproved = false")
    List<User> findUnapprovedUsers();
    
    //@Query("delete from User u where u.username=?1")
    @Query("update User u set u.isDeleted = true where u.username=?1")
    @Modifying
    @Transactional
    void deleteByUsername(String username);

    @Query("update User u set u.isDeleted = true where u.id=?1")
    @Modifying
    @Transactional
    void deleteById(Integer id);

    @Query("UPDATE User u SET u.password = ?1 WHERE u.id = ?2")
    @Modifying
    @Transactional
    void changePassword(String password, Integer id);

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User getById(Integer id);

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    User getLocker(Integer id);

    @Query("update User u set u.isFirstLogin=false where u.id =?1")
    @Modifying
    @Transactional
    void adminsFirstLogin(Integer id);
}
