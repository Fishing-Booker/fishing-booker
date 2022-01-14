package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.ComplaintDTO;
import com.example.fishingbooker.IRepository.IComplaintRepository;
import com.example.fishingbooker.IService.IComplaintService;
import com.example.fishingbooker.IService.IReservationEntityService;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Mapper.ComplaintMapper;
import com.example.fishingbooker.Model.Complaint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintService implements IComplaintService {

    @Autowired
    private IComplaintRepository complaintRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IReservationEntityService entityService;

    @Override
    public Complaint addComplaint(ComplaintDTO dto) {
        Complaint complaint = ComplaintMapper.mapDTOToModel(dto);
        complaint.setClient(userService.findUserById(dto.getClientId()));
        complaint.setReservationEntity(entityService.getEntityById(dto.getEntityId()));
        return complaintRepository.save(complaint);
    }
}
