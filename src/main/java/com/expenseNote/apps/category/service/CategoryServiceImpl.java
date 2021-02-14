package com.expenseNote.apps.category.service;

import com.expenseNote.apps.category.model.Category;
import com.expenseNote.apps.category.repository.CategoryRepository;
import com.expenseNote.apps.user.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    @Override
    public Collection<Category> findByUser(User user) {
        return categoryRepository.findCategoriesByUser(user);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Collection<Category> findAll() {
        return categoryRepository.findAll();
    }
}
