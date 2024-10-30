package com.techradar.polls.techradar_polls.jpa;

import com.techradar.polls.techradar_polls.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<Category, Long> {
    List<Category> findCategoriesByCatName(String catname);
}
