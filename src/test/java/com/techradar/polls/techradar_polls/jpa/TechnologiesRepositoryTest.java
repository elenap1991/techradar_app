package com.techradar.polls.techradar_polls.jpa;

import com.techradar.polls.techradar_polls.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest(properties = {
        "PG_HOST = localhost"

})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TechnologiesRepositoryTest {
    @Autowired
    private TechnologiesRepository technologiesRepository;

    private Technology technology;
    private Ring ring;
    private Status status;
    private Section section;
    private Category category;

    @BeforeEach
    public void setUp() {
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
    }

    @Test
    public void findAllTechnologyFromRepository() throws Exception {
        var result = technologiesRepository.findAll();

        assertNotNull(result);
    }

    @Test
    public void findTechnologyByCategoryFromRepository() throws Exception {
        var result = technologiesRepository.findTechnologyByCategory(category);

        assertNotNull(result);
    }

    @Test
    public void findTechnologyBySectionFromRepository() throws Exception {
        var result = technologiesRepository.findTechnologyBySection(section);

        assertNotNull(result);
    }

    @Test
    public void findNoTechnologyByNullSectionFromRepository() throws Exception {
        section = null;

        var result = technologiesRepository.findTechnologyBySection(section);

        assert (result.size() == 0);
    }

    @Test
    public void findTechnologyBySectionAndCategoryFromRepository() throws Exception {
        var result = technologiesRepository.findTechnologiesByCategoryAndSection(category, section);

        assertNotNull(result);
    }
}
