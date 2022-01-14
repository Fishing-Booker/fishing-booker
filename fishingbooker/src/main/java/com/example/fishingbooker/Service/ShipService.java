package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.NavigationEquipmentDTO;
import com.example.fishingbooker.DTO.UpdateShipDTO;
import com.example.fishingbooker.DTO.lodge.LocationDTO;
import com.example.fishingbooker.DTO.lodge.OwnerDTO;
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
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ShipService implements IShipService {

    @Autowired
    private IShipRepository shipRepository;

    @Autowired
    private ILocationService locationService;

    @Autowired
    private IReservationPeriodOwnerRepository ownerPeriodRepository;

    @Autowired
    private IImageService imageService;

    @Override
    public Ship save(Ship ship) {
        return shipRepository.save(ship);
    }

    @Override
    public List<ShipDTO> findOwnerShips(Integer ownerId) throws IOException {
        List<ShipDTO> ships = new ArrayList<>();
        for (Ship s : shipRepository.findOwnerShips(ownerId)) {
            ships.add(new ShipDTO(s.getId(), ownerId, s.getName(), s.getLocation(), s.getDescription(),
                    s.getAverageGrade(), imageService.getEntityProfileImage(s.getId())));
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
        ship.setImages(null);
        return ship;
    }

    @Override
    public void updateShip(UpdateShipDTO dto, Integer shipId) {
        locationService.updateLocation(dto.getAddress(), dto.getCity(), dto.getCountry(), dto.getLocationId());
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
    public List<ShipInfoDTO> searchAndFilter(String name, String letter) {
        List<Ship> ships = shipRepository.searchAndFilter(name, letter);
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
}
