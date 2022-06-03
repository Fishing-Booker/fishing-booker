package com.example.fishingbooker.controller;

import com.example.fishingbooker.Model.AccountRequest;
import com.example.fishingbooker.util.TestUtil;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountControllerTest {
    /*private static final String URL_PREFIX = "/requests";

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
    public void testGetAllRequests() throws Exception{
        mockMvc.perform(get(URL_PREFIX + "/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @WithMockUser(authorities = "ROLE_ADMIN")
    public void testApproveRequest() throws Exception {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setId(1);
        accountRequest.setRegistrationReason("I want to be part of your platform");
        accountRequest.setUserId("nadja");

        String json = TestUtil.json(accountRequest);

        mockMvc.perform(put(URL_PREFIX + "/approve").contentType(contentType).content(json))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @WithMockUser(authorities = "ROLE_ADMIN")
    public void testRejectRequest() throws Exception {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setId(2);
        accountRequest.setRegistrationReason("I want to be part of your platform");
        accountRequest.setUserId("milan");

        String json = TestUtil.json(accountRequest);

        mockMvc.perform(put(URL_PREFIX + "/reject").contentType(contentType).content(json))
                .andExpect(status().isOk());
    }*/


}
