package com.example.fishingbooker.controller;
import com.example.fishingbooker.DTO.ResponseDTO;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeleteRequestControllerTest {
    private static final String URL_PREFIX = "/deleteRequests";

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
    public void testGetAllDeleteRequests() throws Exception{
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
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setRequestId(1);
        responseDTO.setUserUsername("zoran");
        responseDTO.setResponse("Response approve");

        String json = TestUtil.json(responseDTO);

        mockMvc.perform(put(URL_PREFIX + "/approve").contentType(contentType).content(json))
                .andExpect(status().isOk());

    }

    @Test
    @Transactional
    @Rollback(value = true)
    @WithMockUser(authorities = "ROLE_ADMIN")
    public void testRejectRequest() throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setRequestId(2);
        responseDTO.setUserUsername("nadja");
        responseDTO.setResponse("Response reject");

        String json = TestUtil.json(responseDTO);

        mockMvc.perform(put(URL_PREFIX + "/reject").contentType(contentType).content(json))
                .andExpect(status().isOk());

    }
}
