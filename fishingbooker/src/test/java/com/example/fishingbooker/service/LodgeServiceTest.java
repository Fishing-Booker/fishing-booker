package com.example.fishingbooker.service;

import com.example.fishingbooker.IRepository.ILodgeRepository;
import com.example.fishingbooker.Model.Lodge;
import com.example.fishingbooker.Service.LodgeService;
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
public class LodgeServiceTest {

    @Mock
    private ILodgeRepository lodgeRepository;

    @Mock
    private Lodge lodgeMock;

    @InjectMocks
    private LodgeService lodgeService;

    @Test
    public void getByIdTest() {
        when(lodgeRepository.findLodgeById(1)).thenReturn(lodgeMock);

        Lodge lodge = lodgeService.findById(1);

        Assertions.assertEquals(lodge, lodgeMock);
    }

    @Test
    public void findAllTest() {
        when(lodgeRepository.findAll()).thenReturn(List.of(lodgeMock));

        List<Lodge> lodges = lodgeService.findAll();

        assertThat(lodges).hasSize(1);
    }

    @Test
    public void findLodgeRules() {
        String lodgeRules = "\"No smoking\"#\"No animals\"";
        String rule1 = "\"No smoking\"";
        String rule2 = "\"No animals\"";
        List<String> expectedRules = Arrays.asList(rule1, rule2);

        when(lodgeRepository.findLodgeRules(1)).thenReturn(lodgeRules);

        List<String> rules = lodgeService.findLodgeRules(1);

        assertThat(rules).isEqualTo(expectedRules);
    }

}
