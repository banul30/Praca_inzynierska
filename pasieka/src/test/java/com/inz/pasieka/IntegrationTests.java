package com.inz.pasieka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inz.pasieka.tmpPakiet.dto.UlDTOS.UlDTOPost;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTests {

    @Autowired
    private WebApplicationContext context;


    @Autowired
    private MockMvc mockMvc;



    @WithMockUser(value = "spring")
    @Test
    public void should_not_allow_unauthenticated_user() throws Exception {
        // given and when
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/tests/ul/add")
                .content(asJsonString(new UlDTOPost("nazwa", "1", "korpus", "ramka", 10L, "murzyn")))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(403));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
