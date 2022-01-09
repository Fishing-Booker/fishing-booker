package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.UpdateLodgeDTO;
import com.example.fishingbooker.DTO.lodge.LodgeInfoDTO;
import com.example.fishingbooker.Model.Lodge;

import java.util.List;

public interface ILodgeService {
    Lodge save(Lodge lodge);

    List<Lodge> findAll();

    List<Lodge> findOwnerLodges(Integer ownerId);

    void deleteLodge(Integer lodgeId);

    Lodge findById(Integer lodgeId);

    List<String> findLodgeRules(Integer lodgeId);

    void addRule(String rule, Integer lodgeId);

    void deleteRule(Integer ruleIndex, Integer lodgeId);

    void updateLodge(UpdateLodgeDTO lodgeDTO, Integer lodgeId);

    List<LodgeInfoDTO> getAll();

    List<LodgeInfoDTO> search(String name, String letter, String location);
    
    List<String> getFirstLetters();

    LodgeInfoDTO getById(Integer id);
}
