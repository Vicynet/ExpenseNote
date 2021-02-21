package com.expenseNote.apps.category.repository;
import com.expenseNote.apps.category.model.Category;
import com.expenseNote.apps.user.model.User;
import com.expenseNote.apps.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    Category category;

    User user;

    @Test
    @Transactional
    @Rollback(value = false)
    void createCategoryTest() {
        user = new User();
        Optional<User> getUser = userRepository.findByUsername("codesign");
        if (getUser.isPresent()) {
            log.info("User found -> {}", getUser);
        } else {
            log.info("User does not exist");
        }

        category = Category.builder().categoryName("Food")
                .user(getUser.orElse(user))
                .build();

        assertThat(category.getId()).isNull();
        log.info("Category object created --> {}", category);

        categoryRepository.save(category);
        assertThat(category.getId()).isNotNull();
        log.info("Category object after saving --> {}", category);

    }

    @Test
    void findCategoryByCategoryName() {
        Collection<Category> allCategories = categoryRepository.findAll();

        assertThat(allCategories.size()).isNotNull();
        log.info("Category table is not empty --> {}", allCategories);

        Optional<Category> category = categoryRepository.findCategoryByCategoryName("Food");
        if (category.isPresent()) {
            log.info("Category found -> {}", category);
        } else {
            log.info("Category does not exist");
        }
    }

    @Test
    void findCategoriesByUser() {
        Collection<Category> allCategories = categoryRepository.findAll();

        assertThat(allCategories.size()).isNotNull();
        log.info("Category table is not empty --> {}", allCategories);

        user = userRepository.getOne((long)1);

        Collection<Category> category = categoryRepository.findCategoriesByUser(user);
        assert category != null;
        log.info("Test to retrieve Category by user -> {}", category);
    }

    @Test
    void findCategoryById() {
        Collection<Category> allCategories = categoryRepository.findAll();

        assertThat(allCategories.size()).isNotNull();
        log.info("Category table is not empty --> {}", allCategories);

        category = categoryRepository.findById((long)1).orElse(null);
        assertThat(category).isNotNull();
        log.info("Category with Id (1) exists --> {}", category);
    }

    @Test
    void findAllCategory() {
        Collection<Category> categories = categoryRepository.findAll();
        assertThat(categories).isNotEmpty();
        log.info("All Categories found -> {}", categories);
    }

    @Test
    void updateCategory() {
        Collection<Category> allCategories = categoryRepository.findAll();

        assertThat(allCategories.size()).isNotNull();
        log.info("Category table is not empty --> {}", allCategories);

        category = categoryRepository.findById((long)1).orElse(null);
        assertThat(category).isNotNull();
        log.info("Category with Id (1) exists --> {}", category);

        category.setCategoryName("Transportation");
        category = categoryRepository.save(category);
        assertThat(category.getCategoryName()).isEqualTo("Transportation");
        log.info("After updating category details --> {}", category);
    }

    @Test
    void deleteCategoryById() {
        Collection<Category> allCategories = categoryRepository.findAll();

        assertThat(allCategories.size()).isNotNull();
        log.info("Category table is not empty --> {}", allCategories);

        category = categoryRepository.findById((long)1).orElse(null);
        assertThat(category).isNotNull();
        log.info("Category with Id (1) exists --> {}", category);

        Long categoryId = category.getId();
        categoryRepository.deleteById(categoryId);

        category = categoryRepository.findById((long)1).orElse(null);
        assertThat(category).isNull();
        log.info("After deleting Category from database by id "+ null);
    }
}