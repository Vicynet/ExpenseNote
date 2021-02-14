package com.expenseNote.apps.user.repository;

import com.expenseNote.apps.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail (String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
