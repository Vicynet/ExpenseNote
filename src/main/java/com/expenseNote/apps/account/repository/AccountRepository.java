package com.expenseNote.apps.account.repository;

import com.expenseNote.apps.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
