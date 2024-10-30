package com.techradar.polls.techradar_polls.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResponseTechDto {
    private List<TechnologyDto> technologies;

    public ResponseTechDto() {
        this.technologies = new ArrayList<>();
    }

    public void addTechnology(TechnologyDto technology) {
        this.technologies.add(technology);
    }
}
