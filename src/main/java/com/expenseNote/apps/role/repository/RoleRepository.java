package com.expenseNote.apps.role.repository;

import com.expenseNote.apps.role.model.Role;
import com.expenseNote.apps.role.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleType name);
}
