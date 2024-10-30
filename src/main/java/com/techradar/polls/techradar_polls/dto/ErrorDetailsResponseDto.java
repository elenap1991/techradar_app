package com.techradar.polls.techradar_polls.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorDetailsResponseDto {
    private String message;
    private ErrorDetails details;
}
