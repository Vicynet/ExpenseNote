package com.expenseNote.apps.user.service;

import com.expenseNote.apps.user.model.User;
import com.expenseNote.apps.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @InjectMocks
    UserDetailsService userDetailsService;

    User user;

    Collection<User> users;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
        userDetailsService = new UserServiceImpl();
        user = new User();
        users = new ArrayList<>();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        when(userService.save(user)).thenReturn(user);
        userService.save(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void findById() {
        when(userService.findById(user.getId())).thenReturn(Optional.of(user));
        userService.findById(user.getId());
        verify(userRepository, times(1)).findById(user.getId());
    }

    @Test
    void findByUsername() {
        when(userService.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        userService.findByUsername(user.getUsername());
        verify(userRepository, times(1)).findByUsername(user.getUsername());
    }

    @Test
    void findByEmail() {
        when(userService.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        userService.findByEmail(user.getEmail());
        verify(userRepository, times(1)).findByEmail(user.getEmail());
    }

    @Test
    void update() {
        when(userService.update(user)).thenReturn(user);
        userService.update(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void deleteById() {
        doNothing().when(userRepository).deleteById(user.getId());
        userService.deleteById(user.getId());
        verify(userRepository, times(1)).deleteById(user.getId());
    }

    @Test
    void findAll() {
        when(userService.findAll()).thenReturn(users);
        userService.findAll();
        verify(userRepository, times(1)).findAll();
    }
}