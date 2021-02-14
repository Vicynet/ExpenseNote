package com.expenseNote.apps.category.service;

import com.expenseNote.apps.category.model.Category;
import com.expenseNote.apps.user.model.User;

import java.util.Collection;
import java.util.Optional;

public interface CategoryService {

    Collection<Category> findByUser(User user);

    Category save(Category category);

    Optional<Category> findById(Long categoryId);

    Category update(Category category);

    void deleteById(Long categoryId);

    Collection<Category> findAll();

}
