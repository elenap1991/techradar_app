package com.techradar.polls.techradar_polls.service;

import com.techradar.polls.techradar_polls.dto.ErrorDetails;
import com.techradar.polls.techradar_polls.dto.ErrorDetailsResponseDto;
import com.techradar.polls.techradar_polls.dto.ResponseTechDto;
import com.techradar.polls.techradar_polls.dto.TechnologyDto;
import com.techradar.polls.techradar_polls.jpa.TechnologiesRepository;
import com.techradar.polls.techradar_polls.model.Category;
import com.techradar.polls.techradar_polls.model.Section;
import com.techradar.polls.techradar_polls.model.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

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

    public Technology findTechnologyById(Long id) {
        Technology technology = technologiesRepository.findTechnologiesById(id);
        if (technology == null) {
            throw new ResponseStatusException(NOT_FOUND, INVALID_TECHNOLOGY_ID);
        }
        return technology;
    }

    public ResponseEntity getTechnologies(String categoryName, String sectionName) {
        Category category = null;
        Section section = null;
        ErrorDetails errorDetails = new ErrorDetails();
        boolean isError = false;
        if (categoryName != null) {
            try {
                category = categoriesService.findByCategoryName(categoryName);
            } catch (ResponseStatusException e) {
                errorDetails.setCategory(INVALID_VALUE);
                isError = true;
            }
        }
        if (sectionName != null) {
            try {
                section = sectionService.findSectionByName(sectionName);
            } catch (ResponseStatusException e) {
                errorDetails.setSection(INVALID_VALUE);
                isError = true;
            }
        }
        if (isError) {
            return new ResponseEntity(new ErrorDetailsResponseDto(INVALID_QUERY_PARAMETERS, errorDetails), BAD_REQUEST);
        }
        if(category == null && section == null){
            return new ResponseEntity(getTechnologyResponseDto(findAll()), OK);
        }else if (category == null){
            return new ResponseEntity(getTechnologyResponseDto(findTechnologyBySection(section)), OK);
        }else if(section == null){
            return new ResponseEntity(getTechnologyResponseDto(findTechnologyByCategory(category)), OK);
        } else {
            return new ResponseEntity(getTechnologyResponseDto(findTechnologiesByCategoryAndSection(category, section)), OK);
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
                    tech.getRing().getRingName()
            );
            responseTechDto.addTechnology(techDto);
        }
        return responseTechDto;
    }

    public List<Technology> findTechnologyByCategory(Category category){
        return technologiesRepository.findTechnologyByCategory(category);
    }

    public List<Technology> findTechnologyBySection(Section section){
        return technologiesRepository.findTechnologyBySection(section);
    }

    public List<Technology> findTechnologiesByCategoryAndSection(Category category, Section section){
        return technologiesRepository.findTechnologiesByCategoryAndSection(category, section);
    }
    public List<Technology> findAll(){
        return technologiesRepository.findAll();
    }
}
