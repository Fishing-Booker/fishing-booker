package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.ResponseDTO;
import com.example.fishingbooker.IService.IDeleteAccountRequestService;
import com.example.fishingbooker.Model.DeleteAccountRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/deleteRequests", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class DeleteAccountRequestsController {
    @Autowired
    private IDeleteAccountRequestService deleteAccountRequestService;

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN') || hasRole('DEFADMIN')")
    public List<DeleteAccountRequest> getAllDeleteRequests() {
        return deleteAccountRequestService.findAllUnprocessed();
    }

    @PutMapping("/reject")
    @PreAuthorize("hasRole('ADMIN') || hasRole('DEFADMIN')")
    public void rejectDeleteAccountRequest(@RequestBody ResponseDTO responseDTO) {
        deleteAccountRequestService.rejectDeleteRequest(responseDTO);
    }

    @PutMapping("/approve")
    @PreAuthorize("hasRole('ADMIN') || hasRole('DEFADMIN')")
    public void approveDeleteAccountRequest(@RequestBody ResponseDTO responseDTO) {
        deleteAccountRequestService.approveDeleteRequest(responseDTO);
    }
}
