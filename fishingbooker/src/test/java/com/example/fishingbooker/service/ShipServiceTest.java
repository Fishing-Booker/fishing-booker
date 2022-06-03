package com.example.fishingbooker.service;

import com.example.fishingbooker.DTO.ship.ShipInfoDTO;
import com.example.fishingbooker.IRepository.IShipRepository;
import com.example.fishingbooker.Model.*;
import com.example.fishingbooker.Service.ShipService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ShipServiceTest {

    /*@Mock
    private IShipRepository shipRepository;

    @Mock
    private Ship shipMock;

    @InjectMocks
    private ShipService shipService;

    @Test
    public void getByIdTest() {
        when(shipRepository.findShipById(1)).thenReturn(shipMock);

        Ship ship = shipService.findById(1);

        Assertions.assertEquals(ship, shipMock);
    }

    @Test
    public void findAllTest() {
        when(shipRepository.findAll()).thenReturn(List.of(shipMock));

        List<ShipInfoDTO> ships = shipService.getAll();

        assertThat(ships).hasSize(0);
    }

    @Test
    public void findShipRulesTest() {
        String shipRules = "\"No smoking\"#\"No animals\"";
        String rule1 = "\"No smoking\"";
        String rule2 = "\"No animals\"";
        List<String> expectedRules = Arrays.asList(rule1, rule2);

        when(shipRepository.findShipRules(1)).thenReturn(shipRules);

        List<String> rules = shipService.findShipRules(1);

        assertThat(rules).isEqualTo(expectedRules);
    }

    @Test
    public void findShipNavigationEquipmentTest() {
        String equipment = "Radar#GPS";
        String eq1 = "Radar";
        String eq2 = "GPS";
        List<String> equipmentList = Arrays.asList(eq1, eq2);

        when(shipRepository.findShipNavEquipment(1)).thenReturn(equipment);

        List<String> navigationEquipment = shipService.findShipNavEquipment(1);

        assertThat(navigationEquipment).isEqualTo(equipmentList);
    }

    @Test
    public void findOwnerShipNamesTest() {
        List<String> names = List.of("Ship1", "Ship2");

        when(shipRepository.getOwnerShipNames(1)).thenReturn(names);

        List<String> shipNames = shipService.getOwnerShipNames(1);

        assertThat(shipNames).isEqualTo(names);
    }*/

}
