package com.example.fishingbooker.controller;

import com.example.fishingbooker.DTO.lodge.UpdateLodgeDTO;
import com.example.fishingbooker.util.TestUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LodgeControllerTest {

    /*private static final String URL_PREFIX = "/lodges";

    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeAll
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testFindAllLodges() throws Exception {
        mockMvc.perform(get(URL_PREFIX))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @WithMockUser(authorities = { "ROLE_LODGEOWNER" })
    public void testFindOwnerLodges() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/ownerLodges/{id}", 8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @WithMockUser(authorities = { "ROLE_LODGEOWNER" })
    public void testDeleteLodge() throws Exception {
        mockMvc.perform(delete(URL_PREFIX + "/deleteLodge/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @WithMockUser(authorities = { "ROLE_LODGEOWNER" })
    public void testUpdateLodge() throws Exception {
        UpdateLodgeDTO lodge = new UpdateLodgeDTO("Milanova vikendica", 1,  5, "Description", 0, 1, 1, 1, 1);
        String jsonLodge = TestUtil.json(lodge);

        mockMvc.perform(put(URL_PREFIX + "/updateLodge/{id}", 1)
                        .contentType(contentType).content(jsonLodge))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @WithMockUser(authorities = { "ROLE_LODGEOWNER" })
    public void testAddLodgeRule() throws Exception {
        String rule = "No smoking";
        String jsonRule = TestUtil.json(rule);

        mockMvc.perform(put(URL_PREFIX + "/addRule/{id}", 1)
                        .contentType(contentType).content(jsonRule))
                .andExpect(status().isOk());
    }*/

}
