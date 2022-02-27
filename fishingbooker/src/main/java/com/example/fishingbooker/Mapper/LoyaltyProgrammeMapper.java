package com.example.fishingbooker.Mapper;

import com.example.fishingbooker.DTO.loyaltyProgramme.LoyaltyProgrammeDTO;
import com.example.fishingbooker.Model.LoyaltyProgramme;

public class LoyaltyProgrammeMapper {
    public static LoyaltyProgramme mapDTOToModel(LoyaltyProgrammeDTO dto){
        LoyaltyProgramme loyaltyProgramme = new LoyaltyProgramme();
        loyaltyProgramme.setBronzeLimit(dto.getBronzeLimit());
        loyaltyProgramme.setSilverLimit(dto.getSilverLimit());
        loyaltyProgramme.setGoldLimit(dto.getGoldLimit());
        loyaltyProgramme.setClientIncome(dto.getClientIncome());
        loyaltyProgramme.setOwnerIncome(dto.getOwnerIncome());
        return loyaltyProgramme;
    }
}
