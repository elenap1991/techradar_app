package com.techradar.polls.techradar_polls.controller;

import com.techradar.polls.techradar_polls.dto.ErrorResponseDto;
import com.techradar.polls.techradar_polls.jpa.UsersRepository;
import com.techradar.polls.techradar_polls.service.PollService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/poll")
public class PollsController {
    @Autowired
    private PollService pollService;

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping()
    public ResponseEntity addPoll(@RequestBody Map<String, String> requestBody, HttpServletRequest headers) {
        try {
            Long userId = (long) headers.getAttribute("X-Techradar-UserID");
            var response = pollService.savePoll(requestBody, userId);
            return response;
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponseDto(INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR"), INTERNAL_SERVER_ERROR);
        }
    }

}
