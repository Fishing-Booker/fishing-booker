package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.AdventureDTO;
import com.example.fishingbooker.DTO.EditAdventureDTO;
import com.example.fishingbooker.DTO.adventure.AdventureInfoDTO;
import com.example.fishingbooker.DTO.adventure.AdventureMiniDTO;
import com.example.fishingbooker.Model.Adventure;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface IAdventureService {

    Adventure save(Adventure adventure);

    List<Adventure> findAll();

    List<AdventureMiniDTO> findInstructorAdventures(Integer ownerId) throws IOException;

    void deleteAdventure(Integer id);

    List<AdventureInfoDTO> getAll();

    List<String> getFirstLetters();

    List<AdventureInfoDTO> searchAndFilter(String name, String letter, String location, Integer grade);

    Adventure findById(Integer id);

    void editAdventure(EditAdventureDTO dto);

    AdventureInfoDTO getById(Integer id);

    List<String> getAdventureRules(Integer id);

    void addRule(String rule, Integer adventureId);

    void deleteRule(Integer ruleIndex, Integer adventureId);

    List<AdventureInfoDTO> getByReservationDate(Date date);

    List<AdventureInfoDTO> sortAdventures(String type);

    Adventure fetchById(Integer id);

    void deleteById(Integer id);

    void updateAdventure(Adventure adventure);
}
