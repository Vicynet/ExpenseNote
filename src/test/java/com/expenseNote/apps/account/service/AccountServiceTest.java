package com.expenseNote.apps.account.service;

import com.expenseNote.apps.account.model.Account;
import com.expenseNote.apps.account.repository.AccountRepository;
import com.expenseNote.apps.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.mockito.Mockito.*;

class   AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountService accountService;

    Account account;

    Collection<Account> accounts;

    User user;

    @BeforeEach
    void setUp() {
        accountService = new AccountServiceImpl();
        account = new Account();
        accounts = new ArrayList<>();
        user = new User();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findByExistingUser() {
        when(accountService.findByExistingUser(user)).thenReturn(accounts);
        accountService.findByExistingUser(user);
        verify(accountRepository, times(1)).findByUser(user);
    }

    @Test
    void save() {
        when(accountService.save(account)).thenReturn(account);
        accountService.save(account);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void findById() {
        when(accountService.findById(account.getId())).thenReturn(Optional.of(account));
        accountService.findById(account.getId());
        verify(accountRepository, times(1)).findById(account.getId());
    }

    @Test
    void findByNumber() {
        when(accountService.findByNumber(account.getAccountNumber())).thenReturn(Optional.of(account));
        accountService.findByNumber(account.getAccountNumber());
        verify(accountRepository, times(1)).findByAccountNumber(account.getAccountNumber());
    }

    @Test
    void update() {
        when(accountService.update(account)).thenReturn(account);
        accountService.update(account);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void deleteById() {
        doNothing().when(accountRepository).deleteById(account.getId());
        accountService.deleteById(account.getId());
        verify(accountRepository, times(1)).deleteById(account.getId());
    }

    @Test
    void findAll() {
        when(accountService.findAll()).thenReturn(accounts);
        accountService.findAll();
        verify(accountRepository, times(1)).findAll();
    }
}