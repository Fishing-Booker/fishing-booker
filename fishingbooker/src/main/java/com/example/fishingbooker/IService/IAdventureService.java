package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.adventure.AdventureInfoDTO;
import com.example.fishingbooker.Model.Adventure;
import java.util.List;

public interface IAdventureService {

    Adventure save(Adventure adventure);

    List<Adventure> findAll();

    List<Adventure> findInstructorAdventures(Integer ownerId);

    List<AdventureInfoDTO> getAll();

    List<String> getFirstLetters();

    List<AdventureInfoDTO> searchAndFilter(String name, String letter);

}
