package com.techradar.polls.techradar_polls.service;

import com.techradar.polls.techradar_polls.dto.RequestPollDto;
import com.techradar.polls.techradar_polls.jpa.PollsRepository;
import com.techradar.polls.techradar_polls.model.Poll;
import com.techradar.polls.techradar_polls.model.Ring;
import com.techradar.polls.techradar_polls.model.Technology;
import com.techradar.polls.techradar_polls.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;

@SpringBootTest(
        properties = {"PG_HOST = localhost"}
)
@AutoConfigureMockMvc
public class PollsServiceTest {
    @Mock
    private PollsRepository pollRepository;

    @Mock
    private UserService userService;

    @Mock
    private TechnologiesService technologiesService;

    @Mock
    private RingsService ringsService;

    @InjectMocks
    private PollService pollService;

    private Poll poll;
    private User user;
    private Ring ring;

    @Test
    public void postPollSuccess() throws Exception {
        String ringName = "test";
        user = new User();
        ring = new Ring();
        poll = new Poll(user, 1l, ring, LocalDateTime.now());

        when(pollRepository.save(poll)).thenReturn(poll);
        when(userService.getUserById(1l)).thenReturn(user);
        when(technologiesService.findTechnologyById(1l)).thenReturn(new Technology());
        when(ringsService.getRingByName(ringName)).thenReturn(ring);

        var result = pollService.savePoll(new RequestPollDto(1l, ringName), 1l);

        assertEquals(result.getStatusCode(), CREATED);
    }
}
