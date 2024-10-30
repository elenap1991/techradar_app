package com.techradar.polls.techradar_polls.jpa;

import com.techradar.polls.techradar_polls.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findSectionBySecName(String name);
}
