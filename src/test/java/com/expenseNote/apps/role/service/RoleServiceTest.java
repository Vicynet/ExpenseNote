package com.expenseNote.apps.role.service;

import com.expenseNote.apps.role.model.Role;
import com.expenseNote.apps.role.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.mockito.Mockito.*;

class RoleServiceTest {

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    RoleService roleService;

    Role role;

    @BeforeEach
    void setUp() {
        roleService = new RoleServiceImpl();
        role = new Role();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        when(roleService.save(role)).thenReturn(role);
        roleService.save(role);
        verify(roleRepository, times(1)).save(role);
    }

    @Test
    void findById() {
        when(roleService.findById(1)).thenReturn(Optional.of(role));
        roleService.findById(role.getId());
        verify(roleRepository, times(1)).findById(role.getId());
    }

    @Test
    void update() {
        when(roleService.update(role)).thenReturn(role);
        roleService.update(role);
        verify(roleRepository, times(1)).save(role);
    }

    @Test
    void deleteById() {
        doNothing().when(roleRepository).deleteById(role.getId());
        roleService.deleteById(role.getId());
        verify(roleRepository, times(1)).deleteById(role.getId());
    }

    @Test
    void findAll() {
        Collection<Role> roles = new ArrayList<>();
        when(roleService.findAll()).thenReturn(roles);
        roleService.findAll();
        verify(roleRepository, times(1)).findAll();
    }
}