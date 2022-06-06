package com.example.fishingbooker.controller;

import com.example.fishingbooker.DTO.CompliantResponseDTO;
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
public class ComplaintControllerTest {
   /*private static final String URL_PREFIX = "/complaints";

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
    public void testGetComplaints() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/get"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(1)))
                .andExpect(jsonPath("$.[*].text").value(hasItem("I dont like this")));
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @WithMockUser(authorities = "ROLE_ADMIN")
    public void testRespondComplaint() throws Exception {
        CompliantResponseDTO dto = new CompliantResponseDTO();
        dto.setClientId(5);
        dto.setOwnerId(8);
        dto.setText("Complaint reponse");
        dto.setCompliantId(1);

        String json = TestUtil.json(dto);

        mockMvc.perform(post(URL_PREFIX + "/respond").contentType(contentType).content(json))
                .andExpect(status().is(201));
    }*/

}
