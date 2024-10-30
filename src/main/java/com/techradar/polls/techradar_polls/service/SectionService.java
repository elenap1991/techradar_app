package com.techradar.polls.techradar_polls.service;

import com.techradar.polls.techradar_polls.jpa.SectionRepository;
import com.techradar.polls.techradar_polls.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class SectionService {
    @Autowired
    private SectionRepository sectionRepository;

    public Section findSectionByName(String sectionName) {
        List<Section> section = sectionRepository.findSectionBySecName(sectionName);
        if (section.size() != 1) {
            throw new ResponseStatusException(NOT_FOUND, "Invalid section name: ");
        }
        return section.get(0);
    }
}
