package com.techradar.polls.techradar_polls.dto;

import com.techradar.polls.techradar_polls.model.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class RequestPollDto {
    @NotNull
    private Long tech_id;
    @NotNull
    private String ringResult;
}
