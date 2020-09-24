package com.expenseNote.service.category;

import com.expenseNote.model.Category;
import com.expenseNote.model.User;

import java.util.Collection;
import java.util.Optional;

public interface CategoryService {

    Collection<Category> findByUser(User user);

    Category save(Category category);

    Optional<Category> findById(Long categoryId);

    Category update(Category category);

    void delete(Long categoryId);

    Collection<Category> findAll();

}
