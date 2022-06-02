package com.example.fishingbooker.controller;

import com.example.fishingbooker.DTO.CompliantResponseDTO;
import com.example.fishingbooker.DTO.RatingDTO;
import com.example.fishingbooker.DTO.RatingInfoDTO;
import com.example.fishingbooker.util.TestUtil;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RatingControllerTest {
    private static final String URL_PREFIX = "/rating";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeAll
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @WithMockUser(authorities = "ROLE_ADMIN")
    public void testGetRatings() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/get"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(1)))
                .andExpect(jsonPath("$.[*].comment").value(hasItem("Great experience!")));
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @WithMockUser(authorities = "ROLE_ADMIN")
    public void testApproveRating() throws Exception {
        RatingInfoDTO dto = new RatingInfoDTO();
        dto.setId(1);
        dto.setComment("Great experience!");
        dto.setGrade(5);
        dto.setEntityName("Vikendica kod Milana");
        dto.setClientName("Marko");

        String json = TestUtil.json(dto);

        mockMvc.perform(put(URL_PREFIX + "/approve").contentType(contentType).content(json))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testAddRating() throws Exception {
        RatingDTO ratingDTO = new RatingDTO(3, "Sve pohvale!", 5, 1, 11);

        String json = TestUtil.json(ratingDTO);

        mockMvc.perform(post(URL_PREFIX + "/add").contentType(contentType).content(json))
                .andExpect(status().isCreated());
    }
}
