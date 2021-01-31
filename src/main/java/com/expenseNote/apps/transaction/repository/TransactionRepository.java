package com.expenseNote.apps.transaction.repository;

import com.expenseNote.apps.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
