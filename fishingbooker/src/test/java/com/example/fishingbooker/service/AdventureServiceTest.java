//package com.example.fishingbooker.service;
//
//import com.example.fishingbooker.DTO.adventure.AdventureInfoDTO;
//import com.example.fishingbooker.IRepository.IAdventureRepository;
//import com.example.fishingbooker.Model.*;
//import com.example.fishingbooker.Service.AdventureService;
//import org.assertj.core.api.Assert;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//public class AdventureServiceTest {
//    @Mock
//    private IAdventureRepository adventureRepository;
//
//    @Mock
//    private Adventure adventureMock;
//
//    @InjectMocks
//    private AdventureService adventureService;
//
//    @Test
//    public void getByIdTest(){
//        when(adventureRepository.findAdventureById(1)).thenReturn(adventureMock);
//
//        Adventure adventure = adventureService.findById(1);
//
//        Assertions.assertEquals(adventure, adventureMock);
//    }
//
//    @Test
//    public void findAllTest(){
//        when(adventureRepository.findAll()).thenReturn(Arrays.asList(adventureMock));
//
//        List<Adventure> adventures = adventureService.findAll();
//
//        assertThat(adventures).hasSize(1);
//    }
//
//    @Test
//    public void deleteAdventureTest(){
//        User owner = new User(1, "user", "user123",
//                "User", "User", "user@gmail.com", "address 123",
//                "city", "county", "phone",
//                Arrays.asList(new Role(1, "INSTRUCTOR")));
//
//        Adventure adventure1 = new Adventure(1, owner, "Adventure",
//                new Location(1, 253.5, 12.0, "address", "city", "country"),
//                "description", "rules", 2, 4.5, "biography",
//                10, new ArrayList<Image>(), "fishingEquipment");
//
//        Adventure adventure2 = new Adventure(2, owner, "Adventure",
//                new Location(1, 253.5, 12.0, "address", "city", "country"),
//                "description", "rules", 2, 4.5, "biography",
//                10, new ArrayList<Image>(), "fishingEquipment");
//
//        when(adventureRepository.findAll()).thenReturn(Arrays.asList(adventure1, adventure2));
//        doNothing().when(adventureRepository).deleteAdventure(2);
//
//        Integer dbSizeBeforeRemove = adventureService.findAll().size();
//        adventureService.deleteAdventure(2);
//
//        when(adventureRepository.findAll()).thenReturn(Arrays.asList(adventure1));
//
//        List<Adventure> adventures = adventureService.findAll();
//        assertThat(adventures).hasSize(dbSizeBeforeRemove - 1);
//
//    }
//}
