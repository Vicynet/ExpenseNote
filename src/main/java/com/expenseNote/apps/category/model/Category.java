package com.expenseNote.apps.category.model;

import com.expenseNote.apps.user.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String categoryName;

    @NotBlank
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private User user;

    public Category() {
    }

    public Category(Long id, String categoryName, User user) {
        this.id = id;
        this.categoryName = categoryName;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + getId() +
                ", categoryName='" + getCategoryName() + '\'' +
                ", user=" + getUser() +
                '}';
    }
}
