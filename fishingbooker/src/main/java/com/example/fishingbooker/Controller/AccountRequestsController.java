package com.example.fishingbooker.Controller;

import com.example.fishingbooker.IService.IAccountRequestService;
import com.example.fishingbooker.Model.AccountRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/requests", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class AccountRequestsController {
    @Autowired
    private IAccountRequestService requestService;

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN') || hasRole('DEFADMIN')")
    public List<AccountRequest> getAllRequests() {
        return requestService.findAll();
    }

    @PutMapping("/reject")
    @PreAuthorize("hasRole('ADMIN') || hasRole('DEFADMIN')")
    public void rejectAccountRequest(@RequestBody AccountRequest request) {
        requestService.rejectRequest(request.getUserId());
    }

    @PutMapping("/approve")
    @PreAuthorize("hasRole('ADMIN') || hasRole('DEFADMIN')")
    public void approveAccountRequest(@RequestBody AccountRequest request) {
        requestService.approveRequest(request.getUserId());
    }
}
