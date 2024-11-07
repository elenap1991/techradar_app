package com.techradar.polls.techradar_polls.service;

import com.techradar.polls.techradar_polls.jpa.TechnologiesRepository;
import com.techradar.polls.techradar_polls.model.*;
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
public class TechnologiesServiceTest {
    @Mock
    private TechnologiesRepository technologiesRepository;

    @InjectMocks
    private TechnologiesService technologiesService;

    private Technology technology;
    private Ring ring;
    private Status status;
    private Section section;
    private Category category;

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

    @Test
    public void convertListTechnologyToDto() {
        section = new Section();
        section.setSecId(1);
        section.setSecName("Languages");

        category = new Category();
        category.setCatId(1);
        category.setCatName("Backend");

        status = new Status();
        status.setStatId(1);
        status.setStatName("New");

        ring = new Ring();
        ring.setRingId(1);
        ring.setRingName("MOVED UP");

        technology = new Technology();
        technology.setSection(section);
        technology.setCategory(category);
        technology.setRing(ring);
        technology.setStatus(status);
        technology.setId(1l);
        technology.setName("testName");
        technology.setDescription("testDescription");

        var result = technologiesService.getTechnologyResponseDto(Arrays.asList(technology));

        assertEquals(result.getTechnologies().get(0).getId(), technology.getId());
        assertEquals(result.getTechnologies().get(0).getName(), technology.getName());
        assertEquals(result.getTechnologies().get(0).getDescription(), technology.getDescription());
        assertEquals(result.getTechnologies().get(0).getRing(), technology.getRing().getRingName());
        assertEquals(result.getTechnologies().get(0).getCategory(), technology.getCategory().getCatName());
        assertEquals(result.getTechnologies().get(0).getSection(), technology.getSection().getSecName());
    }

}
