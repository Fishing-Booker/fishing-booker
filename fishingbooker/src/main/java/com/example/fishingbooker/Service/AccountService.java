package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.UserDTO;
import com.example.fishingbooker.IRepository.IAccountRequestRepository;
import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.IService.IAccountRequestService;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Model.AccountRequest;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements IAccountRequestService {

    @Autowired
    IAccountRequestRepository requestRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IUserService userService;

    @Override
    public List<AccountRequest> findUnapprovedRequests() {
        List<AccountRequest> unapprovedRequests = new ArrayList<>();
        for (User u : userService.findUnapprovedUsers()) {
            unapprovedRequests.add(requestRepository.findByUserId(u.getUsername()));
        }
        return unapprovedRequests;
    }

    @Override
    public AccountRequest save(UserDTO userDTO) {
        AccountRequest request = new AccountRequest();
        request.setUserId(userDTO.getUsername());
        request.setRegistrationReason(userDTO.getRegistrationReason());
        return this.requestRepository.save(request);
    }

    @Override
    public List<AccountRequest> findAll() {
        return this.requestRepository.findAll();
    }

    @Override
    public void rejectRequest(String username) {
        requestRepository.deleteById(username);
        User user = userRepository.findByUsername(username);
        userRepository.deleteByUsername(username);
        userService.sendRejectingEmail(user);
    }

    @Override
    public void approveRequest(String username) {
        requestRepository.deleteById(username);
        User user = userRepository.findByUsername(username);
        userRepository.approve(user.getId());
        userService.sendVerificationEmailToOwnersAndInstructors(user);
    }
}
