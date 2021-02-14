package com.expenseNote.apps.transaction.repository;

import com.expenseNote.apps.transaction.model.Transaction;
import com.expenseNote.apps.transaction.model.TransactionType;
import com.expenseNote.apps.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Collection<Transaction> findByUser(User user);
    Optional<Transaction> findByType(TransactionType transactionType);
}
