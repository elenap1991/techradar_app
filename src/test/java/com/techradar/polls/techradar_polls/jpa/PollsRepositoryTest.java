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

public class PollsRepositoryTest {
    @Autowired
    private PollsRepository pollsRepository;

    @Test
    public void testPresenceOfTablePolls() throws Exception {
        var result = pollsRepository.findById(1l);

        assertNotNull(result);
    }
}
