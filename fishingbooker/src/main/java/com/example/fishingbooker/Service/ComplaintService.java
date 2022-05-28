package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.ComplaintDTO;
import com.example.fishingbooker.DTO.ComplaintInfoDTO;
import com.example.fishingbooker.DTO.CompliantResponseDTO;
import com.example.fishingbooker.IRepository.IComplaintRepository;
import com.example.fishingbooker.IRepository.IComplaintResponseRepository;
import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.IService.IComplaintService;
import com.example.fishingbooker.IService.IReservationEntityService;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Mapper.ComplaintMapper;
import com.example.fishingbooker.Mapper.CompliantResponseMapper;
import com.example.fishingbooker.Model.Complaint;
import com.example.fishingbooker.Model.ComplaintResponse;
import com.example.fishingbooker.Model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Autowired
    private IComplaintResponseRepository complaintResponseRepository;

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public Complaint addComplaint(ComplaintDTO dto) {
        Complaint complaint = ComplaintMapper.mapDTOToModel(dto);
        complaint.setClient(userService.findUserById(dto.getClientId()));
        complaint.setReservationEntity(entityService.getEntityById(dto.getEntityId()));
        complaint.setResponded(false);
        var c =  complaintRepository.save(complaint);
        return c;
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

    @Override
    @Transactional
    public void sendCompliantResponse(CompliantResponseDTO dto) {
        try {
            User client = userService.findUserById(dto.getClientId());
            User owner = userService.findUserById(dto.getOwnerId());
            userService.sendEmailCompliantResponse(client, dto.getText());
            userService.sendEmailCompliantResponse(owner, dto.getText());
            ComplaintResponse complaintResponse = CompliantResponseMapper.mapDTOtoModel(dto);
            complaintResponse.setComplaint(complaintRepository.getById(dto.getCompliantId()));
            complaintResponseRepository.save(complaintResponse);
            complaintRepository.updateComplaintToResponded(dto.getCompliantId());
        } catch (OptimisticEntityLockException e) {
            logger.debug("Optimistic lock exception - complaint.");
        }
    }


}
