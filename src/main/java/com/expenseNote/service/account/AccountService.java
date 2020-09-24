package com.expenseNote.service.account;

import com.expenseNote.model.Account;
import com.expenseNote.model.User;

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
