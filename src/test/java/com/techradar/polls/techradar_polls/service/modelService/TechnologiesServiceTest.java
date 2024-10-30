package com.techradar.polls.techradar_polls.service.modelService;

import com.techradar.polls.techradar_polls.jpa.TechnologiesRepository;
import com.techradar.polls.techradar_polls.model.Technology;
import com.techradar.polls.techradar_polls.service.TechnologiesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(
        properties = {"PG_HOST = localhost"}
)
@ExtendWith(MockitoExtension.class)
public class TechnologiesServiceTest {
    @Mock
    private TechnologiesRepository technologiesRepository;

    @InjectMocks
    private TechnologiesService technologiesService;

    @Test
    public void findByTechnologyIdSucessfully() {
        Long id = 1L;

        when(technologiesRepository.findTechnologiesById(id)).thenReturn(
                new Technology());

        assertInstanceOf(Technology.class, technologiesService.findTechnologyById(id));
    }

    @Test
    public void findByTechnologyIdIncorrect() {
        Long id = 1L;

        when(technologiesRepository.findTechnologiesById(id)).thenReturn(null);

        Exception exception = assertThrows(ResponseStatusException.class,
                () -> technologiesService.findTechnologyById(id));

        assertTrue(exception.getMessage().contains("Invalid technology id"));
    }
}
