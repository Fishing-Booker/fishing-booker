package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.UpdateLodgeDTO;
import com.example.fishingbooker.IRepository.ILodgeRepository;
import com.example.fishingbooker.IRepository.IReservationEntityRepository;
import com.example.fishingbooker.IService.ILodgeService;
import com.example.fishingbooker.Model.Lodge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LodgeService implements ILodgeService {

    @Autowired
    private ILodgeRepository lodgeRepository;

    @Autowired
    private IReservationEntityRepository entityRepository;

    @Autowired
    private BedroomService bedroomService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private UserService userService;

    @Override
    public Lodge save(Lodge lodge) {
        return lodgeRepository.save(lodge);
    }

    @Override
    public List<Lodge> findAll() {
        return this.lodgeRepository.findAll();
    }

    @Override
    public List<Lodge> findOwnerLodges(Integer ownerId) {
        List<Lodge> lodges = lodgeRepository.findOwnerLodges(ownerId);
        for (Lodge l : lodges) {
            l.setOwner(null);
            l.setBedrooms(null);
            l.setImages(null);
        }
        return lodges;
    }

    @Override
    public void deleteLodge(Integer lodgeId) {
        lodgeRepository.deleteLodge(lodgeId);
    }

    @Override
    public Lodge findById(Integer lodgeId) {
        Lodge lodge = lodgeRepository.findLodgeById(lodgeId);
        lodge.setOwner(null);
        lodge.setImages(null);
        lodge.setBedrooms(bedroomService.findLodgeBedrooms(lodgeId));
        return lodge;
    }

    @Override
    public List<String> findLodgeRules(Integer lodgeId) {
        String rules = lodgeRepository.findLodgeRules(lodgeId);
        return new ArrayList<>(Arrays.asList(rules.split("#")));
    }

    @Override
    public void addRule(String rule, Integer lodgeId) {
        String[] rules = lodgeRepository.findLodgeRules(lodgeId).split("#");
        String newRules = setNewRules(rules);
        newRules += rule;
        lodgeRepository.addRule(newRules, lodgeId);
    }

    @Override
    public void deleteRule(Integer ruleIndex, Integer lodgeId) {
        String[] rules = lodgeRepository.findLodgeRules(lodgeId).split("#");
        rules[ruleIndex] = "";
        String newRules = String.valueOf(setNewRules(rules));
        newRules = newRules.replaceAll("##", "#");
        lodgeRepository.addRule(newRules, lodgeId);
    }

    @Override
    public void updateLodge(UpdateLodgeDTO dto, Integer lodgeId) {
        locationService.updateLocation(dto.getAddress(), dto.getCity(), dto.getCountry(), dto.getLocationId());
        bedroomService.updateBedroom(dto.getOneBed(), dto.getTwoBed(), dto.getThreeBed(), dto.getFourBed(), lodgeId);
        lodgeRepository.updateLodge(dto.getName(), dto.getDescription(), lodgeId);
    }

    private String setNewRules(String[] rules){
        StringBuilder newRules = new StringBuilder();
        for (String rule : rules) {
            rule = rule.replace("#", "");
            newRules.append(rule);
            newRules.append("#");
        }
        return String.valueOf(newRules);
    }

}
