package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.DeleteAccountRequestDTO;
import com.example.fishingbooker.DTO.ResponseDTO;
import com.example.fishingbooker.IRepository.IDeleteAccountRequestRepository;
import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.IService.IDeleteAccountRequestService;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Model.DeleteAccountRequest;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeleteAccountRequestService implements IDeleteAccountRequestService {
    @Autowired
    IUserService userService;

    @Autowired
    IDeleteAccountRequestRepository repository;

    @Autowired
    IUserRepository userRepository;

    @Override
    public DeleteAccountRequest save(DeleteAccountRequestDTO deleteAccountRequestDTO) {
        DeleteAccountRequest deleteAccountRequest = new DeleteAccountRequest();
        deleteAccountRequest.setDeleteReason(deleteAccountRequestDTO.getReason());
        Integer id = deleteAccountRequestDTO.getId();
        Optional<User> user = userService.findById(id);
        deleteAccountRequest.setUserId(user.get().getUsername());
        deleteAccountRequest.setAccepted(false);
        deleteAccountRequest.setDisapproved(false);
        return this.repository.save(deleteAccountRequest);
    }

    @Override
    public List<DeleteAccountRequest> findAll() {
        return this.repository.findAll();
    }

    @Override
    public void rejectDeleteRequest(ResponseDTO responseDTO) {
        repository.disapprove(responseDTO.getRequestId());
        User user = userRepository.findByUsername(responseDTO.getUserUsername());
        userService.sendEmailResponse(user, responseDTO.getResponse());
    }

    @Override
    public void approveDeleteRequest(ResponseDTO responseDTO) {
        repository.approve(responseDTO.getRequestId());
        User user = userRepository.findByUsername(responseDTO.getUserUsername());
        userRepository.deleteByUsername(user.getUsername());
        userService.sendEmailResponse(user, responseDTO.getResponse());
    }

    @Override
    public List<DeleteAccountRequest> findAllUnprocessed() {
        return this.repository.findAllUnprocessedRequests();
    }
}
