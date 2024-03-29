package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.lodge.UpdateLodgeDTO;
import com.example.fishingbooker.DTO.lodge.LodgeDTO;
import com.example.fishingbooker.DTO.lodge.LodgeInfoDTO;
import com.example.fishingbooker.Model.Lodge;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface ILodgeService {
    Lodge save(Lodge lodge);

    List<Lodge> findAll();

    List<LodgeDTO> findOwnerLodges(Integer ownerId) throws IOException;

    void deleteLodge(Integer lodgeId);

    Lodge findById(Integer lodgeId);

    List<String> findLodgeRules(Integer lodgeId);

    void addRule(String rule, Integer lodgeId);

    void deleteRule(Integer ruleIndex, Integer lodgeId);

    void updateLodge(UpdateLodgeDTO lodgeDTO, Integer lodgeId);

    List<LodgeInfoDTO> getAll();

    List<LodgeInfoDTO> search(String name, String letter, String location, Integer grade, String sortType);
    
    List<String> getFirstLetters();

    LodgeInfoDTO getById(Integer id);

    List<String> getOwnerLodgeNames(Integer ownerId);

    List<LodgeInfoDTO> getByReservationDate(Date date);

    List<LodgeDTO> searchLodgesByName(String name, Integer ownerId) throws IOException;

    List<LodgeInfoDTO> sortLodges(String type);

    Lodge fetchById(Integer id);

    void deleteById(Integer id);

    void updateLodge(Lodge lodge);
}
