package com.techradar.polls.techradar_polls.service.modelService;

import com.techradar.polls.techradar_polls.jpa.CategoriesRepository;
import com.techradar.polls.techradar_polls.model.Category;
import com.techradar.polls.techradar_polls.service.CategoriesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(
        properties = {"PG_HOST = localhost"}
)
@ExtendWith(MockitoExtension.class)
public class CategoriesServiceTest {
    @Mock
    private CategoriesRepository categoriesRepository;

    @InjectMocks
    private CategoriesService categoriesService;

    @Test
    public void findByCategoryNameSucessfully() {
        String categoryName = "test";

        when(categoriesRepository.findCategoriesByCatName(categoryName)).thenReturn(
                Arrays.asList(new Category(1, "test")));

        assertEquals(1, categoriesService.findByCategoryName(categoryName).getCatId());
    }

    @Test
    public void findByCategoryNameIncorrect() {
        when(categoriesRepository.findCategoriesByCatName("test")).thenReturn(
                Arrays.asList());

        Exception exception = assertThrows(ResponseStatusException.class,
                () -> categoriesService.findByCategoryName("test"));

        assertTrue(exception.getMessage().contains("Invalid category name"));
    }
}
