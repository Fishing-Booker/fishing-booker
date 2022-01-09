package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.AdventureDTO;
import com.example.fishingbooker.DTO.EditAdventureDTO;
import com.example.fishingbooker.IRepository.IAdventureRepository;
import com.example.fishingbooker.IService.IAdventureService;
import com.example.fishingbooker.DTO.adventure.AdventureInfoDTO;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Mapper.AdventureMapper;
import com.example.fishingbooker.Model.Adventure;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AdventureService implements IAdventureService {

    @Autowired
    private IAdventureRepository adventureRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private LocationService locationService;

    @Override
    public Adventure save(Adventure adventure) {
        return adventureRepository.save(adventure);
    }

    @Override
    public List<Adventure> findAll() {
        return adventureRepository.findAll();
    }

    @Override
    public List<Adventure> findInstructorAdventures(Integer ownerId) {
        List<Adventure> adventures = adventureRepository.findInstructorAdventures(ownerId);
        for (Adventure a : adventures) {
            a.setOwner(null);
            a.setImages(null);
            a.setReservationPeriods(null);
        }
        return adventures;
    }

    @Override
    public void deleteAdventure(Integer id) {
        adventureRepository.deleteAdventure(id);
    }

    @Override
    public List<AdventureInfoDTO> getAll() {
        List<Adventure> adventures = adventureRepository.getAll();
        List<AdventureInfoDTO> adventuresDTO = new ArrayList<>();
        for (Adventure adventure : adventures) {
            adventuresDTO.add(AdventureMapper.mapToDTO(adventure));
        }
        return adventuresDTO;
    }

    @Override
    public List<String> getFirstLetters() {
        return adventureRepository.getFirstLetters();
    }

    @Override
    public List<AdventureInfoDTO> searchAndFilter(String name, String letter) {
        List<Adventure> adventures = adventureRepository.searchAndFilter(name, letter);
        List<AdventureInfoDTO> adventuresDTO = new ArrayList<>();
        for (Adventure adventure : adventures) {
            adventuresDTO.add(AdventureMapper.mapToDTO(adventure));
        }
        return adventuresDTO;
    }

    @Override
    public Adventure findById(Integer id) {
        Adventure adventure = adventureRepository.findAdventureById(id);
        adventure.setImages(null);
        adventure.setOwner(null);
        adventure.setReservationPeriods(null);
        return adventure;
    }

    @Override
    public void editAdventure(EditAdventureDTO dto) {
        locationService.updateLocation(dto.getAddress(), dto.getCity(), dto.getCountry(), dto.getLocationId());
        adventureRepository.editAdventure(dto.getName(), dto.getDescription(), dto.getBiography(), dto.getMaxPersons(), dto.getCancelConditions(), dto.getFishingEquipment(), dto.getAdventureId());
    }

    @Override
    public AdventureInfoDTO getById(Integer id) {
        Adventure adventure = adventureRepository.findAdventureById(id);
        return AdventureMapper.mapToDTO(adventure);
    }

    @Override
    public List<String> getAdventureRules(Integer id) {
        String rules = adventureRepository.getAdventureRules(id);
        List<String> allRules;
        if(rules.equals("")) {
            allRules = new ArrayList<>();
        } else {
            allRules = new ArrayList<>(Arrays.asList(rules.split("#")));
        }
        return allRules;
    }

    @Override
    public void addRule(String rule, Integer adventureId) {
        String[] rules = adventureRepository.getAdventureRules(adventureId).split("#");
        String newRules = setNewRules(rules);
        newRules +=rule;
        newRules = correctRules(newRules);
        adventureRepository.addRule(newRules, adventureId);
    }

    @Override
    public void deleteRule(Integer ruleIndex, Integer adventureId) {
        String[] rules = adventureRepository.getAdventureRules(adventureId).split("#");
        rules[ruleIndex] = "";
        String newRules = setNewRules(rules);
        newRules = correctRules(newRules);
        adventureRepository.addRule(newRules, adventureId);
    }

    private String setNewRules(String[] rules){
        StringBuilder newRules = new StringBuilder();
        for (String rule : rules) {
            rule = rule.replace("#", "");
            newRules.append("#");
            newRules.append(rule);
        }
        return String.valueOf(newRules);
    }

    private String correctRules(String rules){
        rules = rules.replaceAll("##", "#");
        if(rules.substring(0, 1).contains("#")){
            rules = rules.substring(1);
        }
        return rules;
    }

}
