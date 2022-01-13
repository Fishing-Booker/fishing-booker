package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.UpdateLodgeDTO;
import com.example.fishingbooker.DTO.lodge.LodgeInfoDTO;
import com.example.fishingbooker.IRepository.ILodgeRepository;
import com.example.fishingbooker.IRepository.IReservationEntityRepository;
import com.example.fishingbooker.IService.ILodgeService;
import com.example.fishingbooker.Mapper.LodgeMapper;
import com.example.fishingbooker.Model.Lodge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
            l.setReservationPeriods(null);
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
        lodge.setReservationPeriods(null);
        return lodge;
    }

    @Override
    public List<String> findLodgeRules(Integer lodgeId) {
        String rules = lodgeRepository.findLodgeRules(lodgeId);
        List<String> allRules;
        if(rules.equals("")){
            allRules = new ArrayList<>();
        } else{
            allRules = new ArrayList<>(Arrays.asList(rules.split("#")));
        }
        return allRules;
    }

    @Override
    public void addRule(String rule, Integer lodgeId) {
        String[] rules = lodgeRepository.findLodgeRules(lodgeId).split("#");
        String newRules = setNewRules(rules);
        newRules += rule;
        newRules = correctRules(newRules);
        lodgeRepository.addRule(newRules, lodgeId);
    }

    @Override
    public void deleteRule(Integer ruleIndex, Integer lodgeId) {
        String[] rules = lodgeRepository.findLodgeRules(lodgeId).split("#");
        rules[ruleIndex] = "";
        String newRules = setNewRules(rules);
        newRules = correctRules(newRules);
        lodgeRepository.addRule(newRules, lodgeId);
    }

    @Override
    public void updateLodge(UpdateLodgeDTO dto, Integer lodgeId) {
        locationService.updateLocation(dto.getAddress(), dto.getCity(), dto.getCountry(), dto.getLocationId());
        bedroomService.updateBedroom(dto.getOneBed(), dto.getTwoBed(), dto.getThreeBed(), dto.getFourBed(), lodgeId);
        lodgeRepository.updateLodge(dto.getName(), dto.getMaxPersons(), dto.getDescription(), lodgeId);
    }

    @Override
    public List<LodgeInfoDTO> search(String name, String letter, String location) {
        List<Lodge> lodges = lodgeRepository.search(name, letter, location);
        List<LodgeInfoDTO> lodgesDTO = new ArrayList<>();
        for (Lodge lodge : lodges) {
            lodgesDTO.add(LodgeMapper.mapToDTO(lodge));
        }
        return lodgesDTO;
    }

    @Override
    public List<String> getFirstLetters() {
        return lodgeRepository.getFirstLetters();
    }

    @Override
    public LodgeInfoDTO getById(Integer id) {
        Lodge lodge = lodgeRepository.getLodgeById(id);
        return LodgeMapper.mapToDTO(lodge);
    }

    @Override
    public List<String> getOwnerLodgeNames(Integer ownerId) {
        return lodgeRepository.getOwnerLodgeNames(ownerId);
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

    public List<LodgeInfoDTO> getAll() {
        List<Lodge> lodges = lodgeRepository.getAll();
        List<LodgeInfoDTO> lodgesDTO = new ArrayList<>();
        for (Lodge lodge : lodges) {
            lodgesDTO.add(LodgeMapper.mapToDTO(lodge));
        }
        return lodgesDTO;
    }
    
    private String correctRules(String rules){
        rules = rules.replaceAll("##", "#");
        if(rules.substring(0, 1).contains("#")){
            rules = rules.substring(1);
        }
        return rules;
    }

    @Override
    public List<LodgeInfoDTO> getByReservationDate(Date date) {
        List<Lodge> lodges = lodgeRepository.getByReservationDate(date);
        List<LodgeInfoDTO> lodgesDTO = new ArrayList<>();
        for (Lodge lodge : lodges) {
            lodgesDTO.add(LodgeMapper.mapToDTO(lodge));
        }
        return  lodgesDTO;
    }
}
