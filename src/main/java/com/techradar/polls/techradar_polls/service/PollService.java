package com.techradar.polls.techradar_polls.service;

import com.techradar.polls.techradar_polls.dto.ErrorResponseDto;
import com.techradar.polls.techradar_polls.dto.RequestPollDto;
import com.techradar.polls.techradar_polls.dto.ResponsePollDto;
import com.techradar.polls.techradar_polls.jpa.PollsRepository;
import com.techradar.polls.techradar_polls.model.Poll;
import com.techradar.polls.techradar_polls.model.Ring;
import com.techradar.polls.techradar_polls.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class PollService {
    public static final String RESULT_SUCCESS_ADDED = "Результат опроса успешно добавлен";
    @Autowired
    PollsRepository pollsRepository;

    @Autowired
    TechnologiesService technologiesService;

    @Autowired
    RingsService ringsService;

    @Autowired
    UserService userService;

    public ResponseEntity savePoll(RequestPollDto req, Long userId) {
        User user;
        Ring ring;
        if (req.getTech_id() == null || req.getRingResult() == null) {
            return new ResponseEntity<>(new ErrorResponseDto(BAD_REQUEST.value(), "BAD_REQUEST"), BAD_REQUEST);
        }
        try {
            user = userService.getUserById(userId);
            technologiesService.findTechnologyById(req.getTech_id());
            ring = ringsService.getRingByName(req.getRingResult());
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(new ErrorResponseDto(NOT_FOUND.value(), "TECHNOLOGY_NOT_FOUND"), NOT_FOUND);
        }

        Poll poll = new Poll(user, req.getTech_id(), ring, LocalDateTime.now());
        pollsRepository.save(poll);
        return new ResponseEntity<>(new ResponsePollDto(RESULT_SUCCESS_ADDED), HttpStatus.CREATED);
    }
}
