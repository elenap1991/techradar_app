package com.techradar.polls.techradar_polls.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TechnologyDto {
    private Long id;
    private String name;
    private String description;
    private String category;
    private String section;
    private String ring;
}
