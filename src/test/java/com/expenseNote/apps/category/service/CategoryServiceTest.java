package com.expenseNote.apps.category.service;

import com.expenseNote.apps.category.model.Category;
import com.expenseNote.apps.category.repository.CategoryRepository;
import com.expenseNote.apps.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    Category category;

    Collection<Category> categories;

    User user;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryServiceImpl();
        category = new Category();
        categories = new ArrayList<>();
        user = new User();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findByUser() {
        when(categoryService.findByUser(user)).thenReturn(categories);
        categoryService.findByUser(user);
        verify(categoryRepository, times(1)).findCategoriesByUser(user);
    }

    @Test
    void save() {
        when(categoryService.save(category)).thenReturn(category);
        categoryService.save(category);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void findById() {
        when(categoryService.findById(category.getId())).thenReturn(Optional.of(category));
        categoryService.findById(category.getId());
        verify(categoryRepository, times(1)).findById(category.getId());
    }

    @Test
    void update() {
        when(categoryService.update(category)).thenReturn(category);
        categoryService.update(category);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void deleteById() {
        doNothing().when(categoryRepository).deleteById(category.getId());
        categoryService.deleteById(category.getId());
        verify(categoryRepository, times(1)).deleteById(category.getId());
    }

    @Test
    void findAll() {
        when(categoryService.findAll()).thenReturn(categories);
        categoryService.findAll();
        verify(categoryRepository, times(1)).findAll();
    }
}