package com.techradar.polls.techradar_polls.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TechnologyDto {
    @Schema(example = "2")
    private Long id;
    @Schema(example = "Spring Boot")
    private String name;
    @Schema(example = "Фреймворк для создания микросервисов на Java")
    private String description;
    @Schema(example = "Language")
    private String category;
    @Schema(example = "Backend")
    private String section;
    @Schema(example = "TRIAL")
    private String ring;
}
