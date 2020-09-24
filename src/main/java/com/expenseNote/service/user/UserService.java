package com.expenseNote.service.user;

import com.expenseNote.model.Role;
import com.expenseNote.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    Collection<User> findByRole(Role role);

    User save(User user);

    Optional<User> findById(Long userId);

    User update(User user);

    void deleteById(Long userId);

    Collection<User> findAll();

}
