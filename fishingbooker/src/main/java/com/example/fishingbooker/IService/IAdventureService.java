package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.AdventureDTO;
import com.example.fishingbooker.DTO.EditAdventureDTO;
import com.example.fishingbooker.DTO.adventure.AdventureInfoDTO;
import com.example.fishingbooker.Model.Adventure;
import java.util.List;

public interface IAdventureService {

    Adventure save(Adventure adventure);

    List<Adventure> findAll();

    List<Adventure> findInstructorAdventures(Integer ownerId);

    void deleteAdventure(Integer id);

    List<AdventureInfoDTO> getAll();

    List<String> getFirstLetters();

    List<AdventureInfoDTO> searchAndFilter(String name, String letter);

    Adventure findById(Integer id);

    void editAdventure(EditAdventureDTO dto);

    AdventureInfoDTO getById(Integer id);
}
