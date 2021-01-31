package com.expenseNote.apps.account.service;

import com.expenseNote.apps.account.model.Account;
import com.expenseNote.apps.user.model.User;

import java.util.Collection;
import java.util.Optional;

public interface AccountService {

    Collection<Account> findByUser(User user);

    Account save(Account account);

    Optional<Account> findById(Long accountId);

    Account update(Account account);

    void delete(Long accountId);

    Collection<Account> findAll();

}
