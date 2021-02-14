package com.expenseNote.apps.account.service;

import com.expenseNote.apps.account.model.Account;
import com.expenseNote.apps.account.repository.AccountRepository;
import com.expenseNote.apps.user.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;

    @Override
    public Collection<Account> findByExistingUser(User user) {
        return accountRepository.findByUser(user);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    @Override
    public Optional<Account> findByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public Account update(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void deleteById(Long accountId) {
        accountRepository.deleteById(accountId);
    }

    @Override
    public Collection<Account> findAll() {
        return accountRepository.findAll();
    }
}
