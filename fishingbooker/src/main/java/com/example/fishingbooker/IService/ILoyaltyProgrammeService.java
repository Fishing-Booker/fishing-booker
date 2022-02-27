package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.loyaltyProgramme.LoyaltyProgrammeDTO;
import com.example.fishingbooker.Model.LoyaltyProgramme;

public interface ILoyaltyProgrammeService {
    void add(LoyaltyProgrammeDTO loyaltyProgramme);

    LoyaltyProgramme get();

    void edit(LoyaltyProgrammeDTO loyaltyProgrammeDTO);

    void delete();
}
