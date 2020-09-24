package com.expenseNote.service.transaction;

import com.expenseNote.model.Transaction;
import com.expenseNote.model.User;

import java.util.Collection;
import java.util.Optional;

public interface TransactionService {

    Collection<Transaction> findByUser(User user);

    Transaction save(Transaction transaction);

    Optional<Transaction> findById(Long transactionId);

    Transaction update(Transaction transaction);

    void delete(Long transactionId);

    Collection<Transaction> findAll();

}
