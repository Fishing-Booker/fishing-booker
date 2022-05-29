package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.DeleteAccountRequestDTO;
import com.example.fishingbooker.DTO.ResponseDTO;
import com.example.fishingbooker.Model.DeleteAccountRequest;

import java.util.List;

public interface IDeleteAccountRequestService {
    DeleteAccountRequest save(DeleteAccountRequestDTO deleteAccountRequestDTO);
    List<DeleteAccountRequest> findAll();
    DeleteAccountRequest rejectDeleteRequest(ResponseDTO responseDTO);
    DeleteAccountRequest approveDeleteRequest(ResponseDTO responseDTO);
    List<DeleteAccountRequest> findAllUnprocessed();
}
