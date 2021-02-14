package com.expenseNote.apps.account.repository;

import com.expenseNote.apps.account.model.Account;
import com.expenseNote.apps.account.model.AccountType;
import com.expenseNote.apps.user.model.User;
import com.expenseNote.apps.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.Instant;
import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    Account account;

    User user;

    @Test
    @Transactional
    @Rollback(value = false)
    void createAccountTest() {
        User user = new User();
        Optional<User> getUser = userRepository.findByUsername("codesign");
        if (getUser.isPresent()) {
            log.info("User found -> {}", getUser);
        } else {
            log.info("User does not exist");
        }

        Account account = Account.builder().accountType(AccountType.SAVINGS)
                .accountNumber("1239312589")
                .balance(BigInteger.valueOf(1_200_000))
                .createdAt(Instant.now())
                .user(getUser.orElse(user))
                .build();

        log.info("Account object created --> {}", account);
        accountRepository.save(account);
        assertThat(account.getId()).isNotNull();
        log.info("Account object after saving --> {}", account);
    }

    @Test
    void findByAccountNumber() {
        Collection<Account> allAccounts = accountRepository.findAll();

        assertThat(allAccounts.size()).isNotNull();
        log.info("Account table is not empty --> {}", allAccounts);

        account = accountRepository.findByAccountNumber("0009312577").orElse(null);
        assert account != null;
        assertThat(account.getAccountNumber()).isEqualTo("0009312577");
        log.info("Test to retrieve Account by account number -> {}", account);
    }

    @Test
    void findAccountByUser() {
        Collection<Account> allAccounts = accountRepository.findAll();

        assertThat(allAccounts.size()).isNotNull();
        log.info("Account table is not empty --> {}", allAccounts);

        user = userRepository.getOne((long)1);

        Collection<Account> account = accountRepository.findByUser(user);
        assert account != null;
        log.info("Test to retrieve Account by user -> {}", account);
    }

    @Test
    void findIfAccountExists() {
        Collection<Account> allAccounts = accountRepository.findAll();

        assertThat(allAccounts.size()).isNotNull();
        log.info("Account table is not empty --> {}", allAccounts);

        boolean checkAccount = accountRepository.existsAccountByAccountNumber("0009312577");

        assertThat(checkAccount).isTrue();
        log.info("Account with this number exists -> {}", true);
    }

    @Test
    void updateAccountById() {
        Collection<Account> allAccounts = accountRepository.findAll();

        assertThat(allAccounts.size()).isNotNull();
        log.info("Account table is not empty --> {}", allAccounts);

        account = accountRepository.findById((long)1).orElse(null);
        assertThat(account).isNotNull();
        log.info("Account with Id (1) exists --> {}", account);

        account.setAccountType(AccountType.CURRENT);
        account = accountRepository.save(account);
        assertThat(account.getAccountType()).isEqualTo(AccountType.CURRENT);
        log.info("After updating account details --> {}", account);
    }

    @Test
    void deleteAccountByIdTest() {
        Collection<Account> allAccounts = accountRepository.findAll();

        assertThat(allAccounts.size()).isNotNull();
        log.info("Account table is not empty --> {}", allAccounts);

        account = accountRepository.findById((long)1).orElse(null);
        assertThat(account).isNotNull();
        log.info("Account with Id (1) exists --> {}", account);

        Long accountId = account.getId();
        accountRepository.deleteById(accountId);

        account = accountRepository.findById((long)1).orElse(null);
        assertThat(account).isNull();
        log.info("After deleting Account from database by id "+ null);
    }

    @Test
    void findAllAccount() {
        Collection<Account> accounts = accountRepository.findAll();
        assertThat(accounts).isNotEmpty();
        log.info("All Accounts found -> {}", accounts);
    }

}