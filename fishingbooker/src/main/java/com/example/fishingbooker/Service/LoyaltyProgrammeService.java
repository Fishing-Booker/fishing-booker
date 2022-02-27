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
        return programs.get(0);
    }

    @Override
    public void edit(LoyaltyProgramme loyaltyProgramme) {
        loyaltyProgrammeRepository.editLoyaltyProgramme(loyaltyProgramme);
    }

    @Override
    public void delete() {
        loyaltyProgrammeRepository.deleteLoyaltyProgramme();
    }
}
