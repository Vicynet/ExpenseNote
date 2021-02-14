package com.expenseNote.apps.category.repository;

import com.expenseNote.apps.category.model.Category;
import com.expenseNote.apps.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Collection<Category> findCategoriesByUser(User user);
}
