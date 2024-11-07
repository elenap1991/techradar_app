package com.techradar.polls.techradar_polls;

import com.techradar.polls.techradar_polls.jpa.PollsRepository;
import org.apache.catalina.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        properties = {"PG_HOST = localhost"}
)
@AutoConfigureMockMvc
@Import(SecurityConfig.class)
public class TechradarPollsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PollsRepository pollsRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void postPollWithErrorBadRequest() throws Exception {
        String pollJson = "{\"tech_id\":1,\"ring\":\"TRIAL\"}";
        String messege = "BAD_REQUEST";

        ResultActions result = mockMvc.perform(post("/poll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pollJson));

        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(messege));
    }

    @Test
    public void postPollWithErrorNotFound() throws Exception {
        String pollJson = "{\"tech_id\":100,\"ringResult\":\"TRIAL\"}";
        String messege = "TECHNOLOGY_NOT_FOUND";

        ResultActions result = mockMvc.perform(post("/poll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pollJson));

        result.andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(messege));
    }

    @Test
    public void getTechnologyByCategoryAndSection() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/technology")
                .param("category", "Frontend")
                .param("section", "Tools"));

        result.andExpect(status().isOk());
        assertNotNull(result);
    }

}
