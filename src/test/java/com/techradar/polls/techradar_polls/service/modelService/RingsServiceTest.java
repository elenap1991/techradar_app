package com.techradar.polls.techradar_polls.service.modelService;

import com.techradar.polls.techradar_polls.jpa.RingsRepository;
import com.techradar.polls.techradar_polls.model.Ring;
import com.techradar.polls.techradar_polls.service.RingsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(
        properties = {"PG_HOST = localhost"}
)
@ExtendWith(MockitoExtension.class)
public class RingsServiceTest {
    @Mock
    private RingsRepository ringsRepository;

    @InjectMocks
    private RingsService ringsService;

    @Test
    public void findByRingNameSucessfully() {
        String name = "test";

        when(ringsRepository.findByRingName(name)).thenReturn(
                Arrays.asList(new Ring()));

        assertInstanceOf(Ring.class, ringsService.getRingByName(name));
    }

    @Test
    public void findByRingNameIncorrect() {
        String name = "test";

        when(ringsRepository.findByRingName(name)).thenReturn(Arrays.asList());

        Exception exception = assertThrows(ResponseStatusException.class,
                () -> ringsService.getRingByName(name));

        assertTrue(exception.getMessage().contains("Invalid ring name"));
    }
}
