package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.EntityDTO;
import com.example.fishingbooker.DTO.RatingInfoDTO;
import com.example.fishingbooker.DTO.UserCategoryDTO;
import com.example.fishingbooker.DTO.UserDTO;
import com.example.fishingbooker.Model.Report;
import com.example.fishingbooker.Model.User;
import org.springframework.dao.PessimisticLockingFailureException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> findById(Integer id);

    User findByUsername(String username);

    List<User> findAll();

    User save(UserDTO userDTO) throws PessimisticLockingFailureException;

    void sendVerificationEmail(User user);

    boolean verify(String verificationCode);

    String findUserRolename(Integer id);

    List<User> findUnapprovedUsers();

    void sendVerificationEmailToOwnersAndInstructors(User user);

    void sendRejectingEmail(User user);

    void changePassword(String password, Integer id);

    User update(UserDTO userDTO, Integer id);

    void sendEmailResponseDeleteReq(User user, String response);

    User findUserById(Integer id);

    UserDTO findUserByIdDto(Integer id);

    List<EntityDTO> findUserEntities(Integer userId) throws IOException;

    public List<UserDTO> getUserList(String adminUsername);

    User saveAdmin(UserDTO userDTO);

    void adminFirstLogin(String password, Integer id);

    void deleteUser(Integer id);

    void deleteUserEntity(Integer entityId, Integer userId);

    void sendEmailCompliantResponse(User user, String response);

    void sendEmailApprovedComment(User user, RatingInfoDTO response);

    void sendEmailPenaltyGiven(User user, Report report);

    User doesExist(Integer id);

    User findByEmail(String email);

    UserCategoryDTO getUserCategory(Integer id);

}
