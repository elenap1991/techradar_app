package com.techradar.polls.techradar_polls.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest(properties = {
        "PG_HOST = localhost"
})

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class CategoriesRepositoryTest {
    @Autowired
    private CategoriesRepository categoriesRepository;

    @Test
    public void testPresenceOfTableCategories() throws Exception {
        var result = categoriesRepository.findById(1l);

        assertNotNull(result);
    }
}
