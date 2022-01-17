package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.ComplaintDTO;
import com.example.fishingbooker.DTO.ComplaintInfoDTO;
import com.example.fishingbooker.IRepository.IComplaintRepository;
import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.IService.IComplaintService;
import com.example.fishingbooker.IService.IReservationEntityService;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Mapper.ComplaintMapper;
import com.example.fishingbooker.Model.Complaint;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComplaintService implements IComplaintService {

    @Autowired
    private IComplaintRepository complaintRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IReservationEntityService entityService;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Complaint addComplaint(ComplaintDTO dto) {
        Complaint complaint = ComplaintMapper.mapDTOToModel(dto);
        complaint.setClient(userService.findUserById(dto.getClientId()));
        complaint.setReservationEntity(entityService.getEntityById(dto.getEntityId()));
        complaint.setResponded(false);
        return complaintRepository.save(complaint);
    }

    @Override
    public List<ComplaintInfoDTO> getAll() {
        List<Complaint> allComplaints = complaintRepository.findAll();
        List<ComplaintInfoDTO> dtos = new ArrayList<>();
        for (Complaint complaint : allComplaints) {
            User u = userRepository.getById(complaint.getClient().getId());
            dtos.add(ComplaintMapper.mapModelToDTO(complaint, complaint.getReservationEntity(), u));
        }
        return dtos;

    }
}
