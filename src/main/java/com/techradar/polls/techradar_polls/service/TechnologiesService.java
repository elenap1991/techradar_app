package com.techradar.polls.techradar_polls.service;

import com.techradar.polls.techradar_polls.dto.ErrorDetails;
import com.techradar.polls.techradar_polls.dto.ErrorDetailsResponseDto;
import com.techradar.polls.techradar_polls.dto.ResponseTechDto;
import com.techradar.polls.techradar_polls.dto.TechnologyDto;
import com.techradar.polls.techradar_polls.jpa.TechnologiesRepository;
import com.techradar.polls.techradar_polls.model.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class TechnologiesService {
    public static final String INVALID_VALUE = "Invalid value";
    public static final String INVALID_QUERY_PARAMETERS = "Invalid query parameters";
    public static final String INVALID_TECHNOLOGY_ID = "Invalid technology id";

    @Autowired
    private TechnologiesRepository technologiesRepository;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private SectionService sectionService;

    public List<Technology> findTechnologiesByCategoryAndSection(Long category, Long section) {
        return technologiesRepository.findTechnologiesByCategoryAndSection(category, section);
    }

    public Technology findTechnologyById(Long id) {
        Technology technology = technologiesRepository.findTechnologiesById(id);
        if (technology == null) {
            throw new ResponseStatusException(NOT_FOUND, INVALID_TECHNOLOGY_ID);
        }
        return technology;
    }

    public ResponseEntity getTechnologies(String category, String section) {
        Long categoryId = null;
        Long sectionId = null;
        ErrorDetails errorDetails = new ErrorDetails();
        boolean isError = false;
        if (category != null) {
            try {
                categoryId = categoriesService.findByCategoryName(category).getCatId();
            } catch (ResponseStatusException e) {
                errorDetails.setCategory(INVALID_VALUE);
                isError = true;
            }
        }
        if (section != null) {
            try {
                sectionId = sectionService.findSectionByName(section).getSecId();
            } catch (ResponseStatusException e) {
                errorDetails.setSection(INVALID_VALUE);
                isError = true;
            }
        }
        if (isError) {
            return new ResponseEntity(new ErrorDetailsResponseDto(INVALID_QUERY_PARAMETERS, errorDetails), BAD_REQUEST);
        } else {
            return new ResponseEntity(
                    getTechnologyResponseDto(findTechnologiesByCategoryAndSection(categoryId, sectionId))
                    , HttpStatus.OK
            );
        }
    }

    public ResponseTechDto getTechnologyResponseDto(List<Technology> technologies) {
        ResponseTechDto responseTechDto = new ResponseTechDto();
        for (Technology tech : technologies) {
            TechnologyDto techDto = new TechnologyDto(tech.getId(),
                    tech.getName(),
                    tech.getDescription(),
                    tech.getCategory().getCatName(),
                    tech.getSection().getSecName(),
                    tech.getRing().getRing_name()
            );
            responseTechDto.addTechnology(techDto);
        }
        return responseTechDto;
    }
}
