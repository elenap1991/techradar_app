package com.techradar.polls.techradar_polls.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponsePollDto {
    @Schema(example = "Результат опроса успешно добавлен")
    private String message;
}
