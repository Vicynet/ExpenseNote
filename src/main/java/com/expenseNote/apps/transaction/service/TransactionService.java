package com.expenseNote.apps.transaction.service;

import com.expenseNote.apps.transaction.model.Transaction;
import com.expenseNote.apps.transaction.model.TransactionType;
import com.expenseNote.apps.user.model.User;

import java.util.Collection;
import java.util.Optional;

public interface TransactionService {

    Collection<Transaction> findByUser(User user);

    Transaction save(Transaction transaction);

    Optional<Transaction> findById(Long transactionId);

    Transaction update(Transaction transaction);

    Optional<Transaction> findTransactionByTransactionType(TransactionType transactionType);

    Collection<Transaction> findTransactionsByTransactionType(TransactionType transactionType);

    void deleteById(Long transactionId);

    Collection<Transaction> findAll();

}
