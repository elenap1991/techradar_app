package com.techradar.polls.techradar_polls.dto;

import com.techradar.polls.techradar_polls.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class RequestPollDto {
    @NotNull
    @Schema(description = "Уникальный идентификатор технологии")
    private Long tech_id;
    @NotNull
    @Schema(description = "Результат опроса")
    private String ringResult;
}
