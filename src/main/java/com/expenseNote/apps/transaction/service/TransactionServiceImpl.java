package com.expenseNote.apps.transaction.service;

import com.expenseNote.apps.transaction.model.Transaction;
import com.expenseNote.apps.transaction.model.TransactionType;
import com.expenseNote.apps.transaction.repository.TransactionRepository;
import com.expenseNote.apps.user.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    TransactionRepository transactionRepository;

    @Override
    public Collection<Transaction> findByUser(User user) {
        return transactionRepository.findByUser(user);
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> findById(Long transactionId) {
        return transactionRepository.findById(transactionId);
    }

    @Override
    public Transaction update(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> findByType(TransactionType transactionType) {
        return transactionRepository.findByType(transactionType);
    }

    @Override
    public void deleteById(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    @Override
    public Collection<Transaction> findAll() {
        return transactionRepository.findAll();
    }
}
