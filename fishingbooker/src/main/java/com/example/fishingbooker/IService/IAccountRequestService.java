package com.example.fishingbooker.IService;


import com.example.fishingbooker.DTO.UserDTO;
import com.example.fishingbooker.Model.AccountRequest;

import java.util.List;

public interface IAccountRequestService {
    List<AccountRequest> findUnapprovedRequests();
    AccountRequest save(UserDTO userDTO);
    List<AccountRequest> findAll();
    void rejectRequest(String username);
    void approveRequest(String username);
}
