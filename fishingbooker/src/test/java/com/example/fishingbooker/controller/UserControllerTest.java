package com.example.fishingbooker.controller;

import com.example.fishingbooker.DTO.UserDTO;
import com.example.fishingbooker.Model.User;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {

    private static final String URL_PREFIX = "/users";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());
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
    @WithMockUser(authorities = { "ROLE_ADMIN", "ROLE_DEFADMIN" })
    public void testFindAllUsers() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testDeleteUser() throws Exception {
        mockMvc.perform(put(URL_PREFIX + "/deleteUser/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testUpdateUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("newUsername");
        userDTO.setCity("Beograd");

        String json = TestUtil.json(userDTO);

        mockMvc.perform(put(URL_PREFIX + "/user/{id}", 1).contentType(contentType).content(json))
                .andExpect(status().isOk());
    }
}
