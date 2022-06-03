package com.example.fishingbooker.controller;

import com.example.fishingbooker.util.TestUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PenaltyControllerTest {

    /*private static final String URL_PREFIX = "/penalties";

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
    public void testAnnulPenalties() throws Exception {
        String json = TestUtil.json(12);

        mockMvc.perform(put(URL_PREFIX ).contentType(contentType).content(json))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testFindClientPenalties() throws Exception {
        mockMvc.perform(get(URL_PREFIX ).param("clientId", String.valueOf(1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(3));
    }*/
}
