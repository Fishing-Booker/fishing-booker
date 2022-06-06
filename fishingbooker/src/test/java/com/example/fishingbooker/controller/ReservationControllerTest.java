//package com.example.fishingbooker.controller;
//
//import com.example.fishingbooker.DTO.reservation.ClientReservationDTO;
//import com.example.fishingbooker.util.TestUtil;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import javax.transaction.Transactional;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class ReservationControllerTest {
//    private static final String URL_PREFIX = "/reservations";
//
//    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
//            MediaType.APPLICATION_JSON.getSubtype());
//
//
//    private MockMvc mockMvc;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @BeforeAll
//    public void setup(){
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//
//    @Test
//    @Transactional
//    @Rollback(value = true)
//    public void testCancelReservation() throws Exception {
//        mockMvc.perform(delete(URL_PREFIX + "/cancel/{id}", 2))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @Transactional
//    @Rollback(value = true)
//    public void testGetClientReservations() throws Exception {
//        mockMvc.perform(get(URL_PREFIX + "/{id}", 1))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(contentType))
//                .andExpect(jsonPath("$", hasSize(2)));
//    }
//
//}
