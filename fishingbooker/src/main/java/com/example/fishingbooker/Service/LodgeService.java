package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.lodge.UpdateLodgeDTO;
import com.example.fishingbooker.DTO.lodge.LodgeDTO;
import com.example.fishingbooker.DTO.lodge.LodgeInfoDTO;
import com.example.fishingbooker.IRepository.ILodgeRepository;
import com.example.fishingbooker.IRepository.IReservationEntityRepository;
import com.example.fishingbooker.IService.IImageService;
import com.example.fishingbooker.IService.ILodgeService;
import com.example.fishingbooker.Mapper.LodgeMapper;
import com.example.fishingbooker.Model.Shio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class LodgeService implements ILodgeService {

    @Autowired
    ILodgeRepository lodgeRepository;

    @Autowired
    IReservationEntityRepository entityRepository;

    @Autowired
    BedroomService bedroomService;

    @Autowired
    LocationService locationService;

    @Autowired
    UserService userService;

    @Autowired
    IImageService imageService;

    @Override
    public Shio save(Shio lodge) {
        return lodgeRepository.save(lodge);
    }

    @Override
    public List<Shio> findAll() {
        return this.lodgeRepository.findAll();
    }

    @Override
    public List<LodgeDTO> findOwnerLodges(Integer ownerId) throws IOException {
        List<LodgeDTO> lodges = new ArrayList<>();
        for (Shio l : lodgeRepository.findOwnerLodges(ownerId)) {
            lodges.add(new LodgeDTO(l.getId(), l.getOwner().getId(), l.getName(), l.getLocation(), l.getDescription(),
                    l.getAverageGrade(), imageService.getEntityProfileImage(l.getId()), l.getMaxPersons()));
        }
        return lodges;
    }

    @Override
    public void deleteLodge(Integer lodgeId) {
        lodgeRepository.deleteLodge(lodgeId);
    }

    @Override
    public Shio findById(Integer lodgeId) {
        Shio lodge = lodgeRepository.findLodgeById(lodgeId);
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
        bedroomService.updateBedroom(dto.getOneBed(), dto.getTwoBed(), dto.getThreeBed(), dto.getFourBed(), lodgeId);
        lodgeRepository.updateLodge(dto.getName(), dto.getMaxPersons(), dto.getDescription(), dto.getCancelConditions(), lodgeId);
    }


    @Override
    public List<LodgeInfoDTO> search(String name, String letter, String location, Integer grade, String sortType) {
        List<Shio> lodges = lodgeRepository.search(name, letter, location, Double.valueOf(grade), sortType);
        List<LodgeInfoDTO> lodgesDTO = new ArrayList<>();
        for (Shio lodge : lodges) {
            lodgesDTO.add(LodgeMapper.mapToDTO(lodge));
        }
        return lodgesDTO;
    }

    @Override
    public List<LodgeDTO> searchLodgesByName(String name, Integer owner) throws IOException {
        List<Shio> lodges = lodgeRepository .searchByName(name);
        List<LodgeDTO> lodgesDTO = new ArrayList<>();
        for (Shio l : lodges) {
            if(l.getOwner().getId() == owner && !l.isDeleted()){
                lodgesDTO.add(new LodgeDTO(l.getId(), l.getOwner().getId(), l.getName(), l.getLocation(), l.getDescription(),
                        l.getAverageGrade(), imageService.getEntityProfileImage(l.getId()), l.getMaxPersons()));
            }
        }
        return lodgesDTO;
    }

    @Override
    public List<String> getFirstLetters() {
        return lodgeRepository.getFirstLetters();
    }

    @Override
    public LodgeInfoDTO getById(Integer id) {
        Shio lodge = lodgeRepository.getLodgeById(id);
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
        List<Shio> lodges = lodgeRepository.getAll();
        List<LodgeInfoDTO> lodgesDTO = new ArrayList<>();
        for (Shio lodge : lodges) {
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
        List<Shio> lodges = lodgeRepository.getByReservationDate(date);
        List<LodgeInfoDTO> lodgesDTO = new ArrayList<>();
        for (Shio lodge : lodges) {
            lodgesDTO.add(LodgeMapper.mapToDTO(lodge));
        }
        return  lodgesDTO;
    }

    @Override
    public List<LodgeInfoDTO> sortLodges(String type) {
        List<Shio> lodges = new ArrayList<>();
        switch (type) {
            case "nameA":
                lodges = lodgeRepository.sortByNameAscending();
                break;
            case "nameD":
                lodges = lodgeRepository.sortByNameDescending();
                break;
            case "gradeA":
                lodges = lodgeRepository.sortByGradeAscending();
                break;
            case "gradeD":
                lodges = lodgeRepository.sortByGradeDescending();
                break;
            default:
                break;
        }

        List<LodgeInfoDTO> lodgesDTO = new ArrayList<>();
        for (Shio lodge : lodges) {
            lodgesDTO.add(LodgeMapper.mapToDTO(lodge));
        }
        return lodgesDTO;
    }

    @Override
    @Cacheable(value = "lodge", key = "'LodgeInCache'+#id")
    public Shio fetchById(Integer id) {
        return lodgeRepository.findById(id).get();
    }

    @Override
    @CacheEvict(value = "lodge", key = "'LodgeInCache'+#id")
    public void deleteById(Integer id) {
        lodgeRepository.deleteById(id);
    }

    @Override
    @CachePut(value = "lodge", key = "'LodgeInCache'+#lodge.id")
    public void updateLodge(Shio lodge) {
        lodgeRepository.updateLodge(lodge.getName(),
                lodge.getMaxPersons(),
                lodge.getDescription(),
                lodge.getCancelConditions(),
                lodge.getId());
    }
}
