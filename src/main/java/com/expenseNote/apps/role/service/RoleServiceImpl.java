package com.expenseNote.apps.role.service;

import com.expenseNote.apps.role.model.Role;
import com.expenseNote.apps.role.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findById(Integer roleId) {
        return roleRepository.findById(roleId);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteById(Integer roleId) {
        roleRepository.deleteById(roleId);
    }

    @Override
    public Collection<Role> findAll() {
        return roleRepository.findAll();
    }
}
