package com.techradar.polls.techradar_polls.service;

import com.techradar.polls.techradar_polls.jpa.CategoriesRepository;
import com.techradar.polls.techradar_polls.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    public Category findByCategoryName(String categoryName) {
        List<Category> category = categoriesRepository.findCategoriesByCatName(categoryName);
        if (category.size() != 1) {
            throw new ResponseStatusException(NOT_FOUND, "Invalid category name");
        }
        return category.get(0);
    }
}
