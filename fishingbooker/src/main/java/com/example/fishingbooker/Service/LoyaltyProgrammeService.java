package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.loyaltyProgramme.LoyaltyProgrammeDTO;
import com.example.fishingbooker.IRepository.ILoyaltyProgrammeRepository;
import com.example.fishingbooker.IService.ILoyaltyProgrammeService;
import com.example.fishingbooker.Mapper.LoyaltyProgrammeMapper;
import com.example.fishingbooker.Model.LoyaltyProgramme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoyaltyProgrammeService implements ILoyaltyProgrammeService {

    @Autowired
    ILoyaltyProgrammeRepository loyaltyProgrammeRepository;

    @Override
    public void add(LoyaltyProgrammeDTO dto) {
        LoyaltyProgramme loyaltyProgramme = LoyaltyProgrammeMapper.mapDTOToModel(dto);
        loyaltyProgrammeRepository.save(loyaltyProgramme);
    }

    @Override
    public LoyaltyProgramme get() {
        List<LoyaltyProgramme> programs = loyaltyProgrammeRepository.findAll();
        if(programs.size()==0) {
            return null;
        } else {
            return programs.get(0);
        }
    }

    @Override
    public void edit(LoyaltyProgrammeDTO loyaltyProgrammeDTO) {
        LoyaltyProgramme loyaltyProgramme = LoyaltyProgrammeMapper.mapDTOToModel(loyaltyProgrammeDTO);
        List<LoyaltyProgramme> oldLP = loyaltyProgrammeRepository.findAll();
        loyaltyProgrammeRepository.deleteLoyaltyProgramme(oldLP.get(0).getId());
        loyaltyProgrammeRepository.save(loyaltyProgramme);

    }

    @Override
    public void delete() {
        List<LoyaltyProgramme> oldLP = loyaltyProgrammeRepository.findAll();
        loyaltyProgrammeRepository.deleteLoyaltyProgramme(oldLP.get(0).getId());
    }
}
