package com.expenseNote.apps.transaction.repository;

import com.expenseNote.apps.category.model.Category;
import com.expenseNote.apps.transaction.model.Transaction;
import com.expenseNote.apps.transaction.model.TransactionType;
import com.expenseNote.apps.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Collection<Transaction> findByUser(User user);
    Optional<Transaction> findTransactionByCategory(Category category);
    Collection<Transaction> findTransactionsByCategory(Category category);
    Collection<Transaction> findTransactionsByTransactionType(TransactionType transactionType);
    Optional<Transaction> findTransactionByTransactionType(TransactionType transactionType);
}
