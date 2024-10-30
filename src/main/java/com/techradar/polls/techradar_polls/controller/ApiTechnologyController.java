package com.techradar.polls.techradar_polls.controller;

import com.techradar.polls.techradar_polls.dto.ErrorResponseDto;
import com.techradar.polls.techradar_polls.service.TechnologiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/technology")
public class ApiTechnologyController {

    @Autowired
    private TechnologiesService technologiesService;

    @GetMapping
    public ResponseEntity getTechnologies(@RequestParam(required = false) String category,
                                          @RequestParam(required = false) String section) {
        ResponseEntity test;
        try {
            return technologiesService.getTechnologies(category, section);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorResponseDto(INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR"), INTERNAL_SERVER_ERROR);
        }
    }
}
