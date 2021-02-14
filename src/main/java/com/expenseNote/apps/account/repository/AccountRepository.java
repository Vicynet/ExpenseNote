package com.expenseNote.apps.account.repository;

import com.expenseNote.apps.account.model.Account;
import com.expenseNote.apps.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(String accountNumber);

    Collection<Account> findByUser(User user);

    Boolean existsAccountByAccountNumber (String accountNumber);

}
