package com.techradar.polls.techradar_polls.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class ErrorDetails {
    @Schema(example = "Invalid value")
    private String category;
    @Schema(example = "Invalid value")
    private String section;
}
