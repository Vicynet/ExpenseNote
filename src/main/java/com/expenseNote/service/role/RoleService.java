package com.expenseNote.service.role;

import com.expenseNote.model.Role;

import java.util.Collection;
import java.util.Optional;

public interface RoleService {

    Role save(Role role);

    Optional<Role> findById(Long roleId);

    Role update(Role role);

    void delete(Long roleId);

    Collection<Role> findAll();
    
}
