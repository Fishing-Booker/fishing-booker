package com.example.fishingbooker.controller;

import com.example.fishingbooker.DTO.SubscriberDTO;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SubscriberControllerTest {
    private static final String URL_PREFIX = "/subscribe";

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
    public void testSubscribe() throws Exception {
        SubscriberDTO subscriberDTO = new SubscriberDTO(3, 1);
        String toJson = TestUtil.json(subscriberDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(URL_PREFIX ).contentType(contentType).content(toJson))
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testUnsubscribe() throws Exception {
        mockMvc.perform(delete(URL_PREFIX + "/unsubscribe")
                .param("entityId", String.valueOf(1))
                .param("userId", String.valueOf(3)))
                .andExpect(jsonPath("$").value(true))
                .andExpect(status().isOk());
    }
}
