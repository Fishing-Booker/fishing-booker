package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.lodge.LocationDTO;
import com.example.fishingbooker.DTO.lodge.LodgeInfoDTO;
import com.example.fishingbooker.DTO.lodge.OwnerDTO;
import com.example.fishingbooker.IRepository.ILodgeRepository;
import com.example.fishingbooker.IService.ILodgeService;
import com.example.fishingbooker.Model.Lodge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LodgeService implements ILodgeService {

    @Autowired
    private ILodgeRepository lodgeRepository;

    @Override
    public Lodge save(Lodge lodge) {
        return lodgeRepository.save(lodge);
    }

    @Override
    public List<LodgeInfoDTO> getAll() {
        List<Lodge> lodges = lodgeRepository.findAll();
        List<LodgeInfoDTO> lodgesDTO = new ArrayList<>();

        for (Lodge lodge : lodges) {
            LodgeInfoDTO dto = new LodgeInfoDTO();
            dto.setName(lodge.getName());
            dto.setDescription(lodge.getDescription());
            dto.setAverageGrade(lodge.getAverageGrade());
            dto.setRules(lodge.getRules());
            dto.setCancelConditions(lodge.getCancelConditions());
            dto.setLocation(new LocationDTO(lodge.getLocation().getAddress(), lodge.getLocation().getCity(), lodge.getLocation().getCountry()));
            dto.setBedroom(null);
            dto.setImages(null);
            dto.setOwner(new OwnerDTO(lodge.getOwner().getName(), lodge.getOwner().getSurname()));
            lodgesDTO.add(dto);
        }
        return lodgesDTO;
    }
}
