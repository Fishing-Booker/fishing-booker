package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.ComplaintDTO;
import com.example.fishingbooker.DTO.ComplaintInfoDTO;
import com.example.fishingbooker.IService.IComplaintService;
import com.example.fishingbooker.Model.Complaint;
import com.example.fishingbooker.Model.ReservationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/complaints", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ComplaintController {

    @Autowired
    IComplaintService complaintService;

    @PostMapping("/add")
    public ResponseEntity<Complaint> addComplaint(@RequestBody ComplaintDTO dto) {
        complaintService.addComplaint(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public List<ComplaintInfoDTO> getComplaints(){
        return complaintService.getAll();
    }


}
