package com.techradar.polls.techradar_polls.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorDetailsResponseDto {
    @Schema(example = "Invalid query parameters")
    private String error;
    private ErrorDetails details;
}
