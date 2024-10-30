package com.techradar.polls.techradar_polls.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techradar.polls.techradar_polls.jpa.PollsRepository;
import com.techradar.polls.techradar_polls.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(
        properties = {"PG_HOST = localhost"}
)
@ExtendWith(MockitoExtension.class)
public class PollsServiceTest {
    @Mock
    private PollsRepository pollsRepository;

    @Mock
    private TechnologiesService technologiesService;

    @Mock
    private RingsService ringsService;

    @Mock
    UserService userService;

    @InjectMocks
    private PollService pollService;

    @Mock
    private ObjectMapper objectMapper;

    @Test
    public void test1(){
        String categoryName = "test";
        Long id = 1L;
        HashMap<String, String> req = new HashMap<>();
        req.put("tech_id", "100");
        req.put("ringResult", "TRIAL");
        when(userService.getUserById(id)).thenReturn(new User());
        when(technologiesService.findTechnologyById(id)).thenReturn(new Technology());
        when(ringsService.getRingByName("name")).thenReturn(new Ring());
        when(pollsRepository.save(new Poll())).thenReturn(new Poll());

        assertEquals(1, pollService.savePoll(req, id).getBody());

    }

}
