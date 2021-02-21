package com.expenseNote.apps.transaction.service;

import com.expenseNote.apps.transaction.model.Transaction;
import com.expenseNote.apps.transaction.repository.TransactionRepository;
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

class TransactionServiceTest {

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    TransactionService transactionService;

    Transaction transaction;

    Collection<Transaction> transactions;

    User user;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionServiceImpl();
        transaction = new Transaction();
        transactions = new ArrayList<>();
        user = new User();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findByUser() {
        when(transactionService.findByUser(user)).thenReturn(transactions);
        transactionService.findByUser(user);
        verify(transactionRepository, times(1)).findByUser(user);
    }

    @Test
    void save() {
        when(transactionService.save(transaction)).thenReturn(transaction);
        transactionService.save(transaction);
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    void findById() {
        when(transactionService.findById(transaction.getId())).thenReturn(Optional.of(transaction));
        transactionService.findById(transaction.getId());
        verify(transactionRepository, times(1)).findById(transaction.getId());
    }

    @Test
    void update() {
        when(transactionService.update(transaction)).thenReturn(transaction);
        transactionService.update(transaction);
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    void findTransactionByTransactionType() {
        when(transactionService.findTransactionByTransactionType(transaction.getTransactionType())).thenReturn(Optional.of(transaction));
        transactionService.findTransactionByTransactionType(transaction.getTransactionType());
        verify(transactionRepository, times(1)).findTransactionByTransactionType(transaction.getTransactionType());
    }

    @Test
    void findTransactionsByTransactionType() {
        when(transactionService.findTransactionsByTransactionType(transaction.getTransactionType())).thenReturn(transactions);
        transactionService.findTransactionsByTransactionType(transaction.getTransactionType());
        verify(transactionRepository, times(1)).findTransactionsByTransactionType(transaction.getTransactionType());
    }

    @Test
    void deleteById() {
        doNothing().when(transactionRepository).deleteById(transaction.getId());
        transactionService.deleteById(transaction.getId());
        verify(transactionRepository, times(1)).deleteById(transaction.getId());
    }

    @Test
    void findAll() {
        when(transactionService.findAll()).thenReturn(transactions);
        transactionService.findAll();
        verify(transactionRepository, times(1)).findAll();
    }
}