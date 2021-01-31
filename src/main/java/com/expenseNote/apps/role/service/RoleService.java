package com.expenseNote.apps.role.service;

import com.expenseNote.apps.role.model.Role;

import java.util.Collection;
import java.util.Optional;

public interface RoleService {

    Role save(Role role);

    Optional<Role> findById(Integer roleId);

    Role update(Role role);

    void deleteById(Integer roleId);

    Collection<Role> findAll();

}
