package com.expenseNote.apps.user.service;

import com.expenseNote.apps.user.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> findById(Long userId);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User update(User user);

    void deleteById(Long userId);

    Collection<User> findAll();

}
