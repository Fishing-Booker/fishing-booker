package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.ship.UpdateShipDTO;
import com.example.fishingbooker.DTO.ship.ShipDTO;
import com.example.fishingbooker.DTO.ship.ShipInfoDTO;
import com.example.fishingbooker.IRepository.IReservationPeriodOwnerRepository;
import com.example.fishingbooker.IRepository.IShipRepository;
import com.example.fishingbooker.IService.IImageService;
import com.example.fishingbooker.IService.ILocationService;
import com.example.fishingbooker.IService.IShipService;
import com.example.fishingbooker.Mapper.ShipMapper;
import com.example.fishingbooker.Model.Ship;
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
public class ShipService implements IShipService {

    //@Autowired
    private final IShipRepository shipRepository;

    @Autowired
    private ILocationService locationService;

    @Autowired
    private IReservationPeriodOwnerRepository ownerPeriodRepository;

    @Autowired
    private IImageService imageService;

    public ShipService(IShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    @Override
    public Ship save(Ship ship) {
        return shipRepository.save(ship);
    }

    @Override
    public List<ShipDTO> findOwnerShips(Integer ownerId) throws IOException {
        List<ShipDTO> ships = new ArrayList<>();
        for (Ship s : shipRepository.findOwnerShips(ownerId)) {
            ships.add(new ShipDTO(s.getId(), ownerId, s.getName(), s.getLocation(), s.getDescription(),
                    s.getAverageGrade(), imageService.getEntityProfileImage(s.getId()), s.getMaxPersons()));
        }
        return ships;
    }

    @Override
    public void deleteShip(Integer shipId) {
        shipRepository.deleteShip(shipId);
    }

    @Override
    public Ship findById(Integer shipId) {
        Ship ship = shipRepository.findShipById(shipId);
        ship.setOwner(null);
        return ship;
    }

    @Override
    public void updateShip(UpdateShipDTO dto, Integer shipId) {
        shipRepository.updateShip(dto.getName(), dto.getDescription(), dto.getShipType(), dto.getLength(),
                dto.getEngineNumber(), dto.getEnginePower(), dto.getMaxSpeed(), dto.getCapacity(), shipId);
    }

    @Override
    public List<String> findShipRules(Integer shipId) {
        String rules = shipRepository.findShipRules(shipId);
        List<String> allRules;
        if(rules.equals("")){
            allRules = new ArrayList<>();
        } else{
            allRules = new ArrayList<>(Arrays.asList(rules.split("#")));
        }
        return allRules;
    }

    @Override
    public void addRule(String rule, Integer shipId) {
        String[] rules = shipRepository.findShipRules(shipId).split("#");
        String newRules = setNewRules(rules);
        newRules += rule;
        newRules = correctRules(newRules);
        shipRepository.addRule(newRules, shipId);
    }

    @Override
    public void deleteRule(Integer ruleIndex, Integer shipId) {
        String[] rules = shipRepository.findShipRules(shipId).split("#");
        rules[ruleIndex] = "";
        String newRules = setNewRules(rules);
        newRules = correctRules(newRules);
        shipRepository.addRule(newRules, shipId);
    }

    @Override
    public List<ShipInfoDTO> getAll() {
        List<Ship> ships = shipRepository.getAll();
        List<ShipInfoDTO> shipsDTO = new ArrayList<>();
        for (Ship ship : ships) {
            shipsDTO.add(ShipMapper.mapToDTO(ship));
        }
        return shipsDTO;
    }

    @Override
    public List<String> getFirstLetters() {
        return shipRepository.getFirstLetters();
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

    private String correctRules(String rules){
        rules = rules.replaceAll("##", "#");
        if(rules.substring(0, 1).contains("#")){
            rules = rules.substring(1);
        }
        return rules;
    }

    @Override
    public List<ShipInfoDTO> searchAndFilter(String name, String letter, String location, Integer grade) {
        List<Ship> ships = shipRepository.searchAndFilter(name, letter, location, Double.valueOf(grade));
        List<ShipInfoDTO> shipsDTO = new ArrayList<>();
        for (Ship ship : ships) {
            shipsDTO.add(ShipMapper.mapToDTO(ship));
        }
        return shipsDTO;
    }

    @Override
    public ShipInfoDTO getById(Integer id) {
        Ship ship = shipRepository.findShipById(id);
        return ShipMapper.mapToDTO(ship);
    }

    @Override
    public List<ShipInfoDTO> getByReservationDate(Date date) {
        List<Ship> ships = shipRepository.getByReservationDate(date);
        List<ShipInfoDTO> shipsDTO = new ArrayList<>();
        for (Ship ship : ships) {
            shipsDTO.add(ShipMapper.mapToDTO(ship));
        }
        return shipsDTO;
    }

    @Override
    public List<String> findShipNavEquipment(Integer shipId) {
        String eq = shipRepository.findShipNavEquipment(shipId);
        return getCorrectedEq(eq);
    }

    private List<String> getCorrectedEq(String eq){
        List<String> allEq = new ArrayList<>();
        if(!eq.equals("")){
            for (String navEq : eq.split("#")) {
                navEq = navEq.replaceAll("\"", "");
                allEq.add(navEq);
            };
        }
        return allEq;
    }

    @Override
    public void addNavEquipment(String equipment, Integer shipId) {
        shipRepository.addNavEquipment(equipment, shipId);
    }

    @Override
    public void deleteNavEquipment(Integer eqIndex, Integer shipId) {
        String[] eq = shipRepository.findShipNavEquipment(shipId).split("#");
        eq[eqIndex] = "";
        String newEq = setNewRules(eq);
        newEq = correctRules(newEq);
        shipRepository.addNavEquipment(newEq, shipId);
    }

    @Override
    public List<String> getOwnerShipNames(Integer ownerId) {
        return shipRepository.getOwnerShipNames(ownerId);
    }

    @Override
    public List<ShipDTO> searchShipsByName(String name, Integer owner) throws IOException {
        List<Ship> ships = shipRepository .searchByName(name);
        List<ShipDTO> shipsDTO = new ArrayList<>();
        for (Ship s : ships) {
            if(s.getOwner().getId() == owner && !s.isDeleted()){
                shipsDTO.add(new ShipDTO(s.getId(), s.getOwner().getId(), s.getName(), s.getLocation(), s.getDescription(),
                        s.getAverageGrade(), imageService.getEntityProfileImage(s.getId()), s.getMaxPersons()));
            }
        }
        return shipsDTO;
    }

    @Override
    public List<ShipInfoDTO> sortShips(String type) {
        List<Ship> ships = new ArrayList<>();
        switch (type) {
            case "nameA":
                ships = shipRepository.sortByNameAscending();
                break;
            case "nameD":
                ships = shipRepository.sortByNameDescending();
                break;
            case "gradeA":
                ships = shipRepository.sortByGradeAscending();
                break;
            case "gradeD":
                ships = shipRepository.sortByGradeDescending();
                break;
            default:
                break;
        }

        List<ShipInfoDTO> shipsDTO = new ArrayList<>();
        for (Ship ship : ships) {
            shipsDTO.add(ShipMapper.mapToDTO(ship));
        }
        return shipsDTO;
    }

    @Override
    @Cacheable(value = "ship", key = "'ShipInCache'+#id")
    public Ship fetchById(Integer id) {
        return shipRepository.findById(id).get();
    }

    @Override
    @CacheEvict(value = "ship", key = "'ShipInCache'+#id")
    public void deleteById(Integer id) {
        shipRepository.deleteById(id);
    }

    @Override
    @CachePut(value = "ship", key = "'ShipInCache'+#ship.id")
    public void updateShip(Ship ship) {
        shipRepository.updateShip(ship.getName(),
                ship.getDescription(),
                ship.getShipType(),
                ship.getLength(),
                ship.getEngineNumber(),
                ship.getEnginePower(),
                ship.getMaxSpeed(),
                0,
                ship.getId());
    }
}
