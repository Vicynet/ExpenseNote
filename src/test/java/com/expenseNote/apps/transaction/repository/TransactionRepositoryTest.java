package com.expenseNote.apps.transaction.repository;

import com.expenseNote.apps.account.model.Account;
import com.expenseNote.apps.account.repository.AccountRepository;
import com.expenseNote.apps.category.model.Category;
import com.expenseNote.apps.category.repository.CategoryRepository;
import com.expenseNote.apps.transaction.model.Transaction;
import com.expenseNote.apps.transaction.model.TransactionType;
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

@Slf4j
@SpringBootTest
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    Transaction transaction;

    Account account;

    Category category;

    User user;

    @Test
    @Transactional
    @Rollback(value = false)
    void createTransactionTest() {
        User user = new User();
        Optional<User> getUser = userRepository.findByUsername("codesign");
        if (getUser.isPresent()) {
            log.info("User found -> {}", getUser);
        } else {
            log.info("User does not exist");
        }

        Category category = new Category();
        Optional<Category> getCategory = categoryRepository.findCategoryByCategoryName("Food");
        if (getCategory.isPresent()) {
            log.info("Category found -> {}", getCategory);
        } else {
            log.info("Category does not exist");
        }

        account = new Account();
        BigInteger getAccountBalance = BigInteger.valueOf(0);
        BigInteger accountBalance;
        BigInteger newAccountBalance;

        Optional<Account> getAccount = accountRepository.findByAccountNumber("0009312577");
        if (getAccount.isPresent()) {
            log.info("Account found -> {}", getAccount);
            getAccountBalance = getAccount.get().getBalance();
        } else {
            log.info("Account does not exist");
        }
        accountBalance = getAccountBalance;

        transaction = Transaction.builder().amount(BigInteger.valueOf(500_000))
                .description("For Food")
                .title("For Food again")
                .transactionType(TransactionType.EXPENSE)
                .account(getAccount.orElse(account))
                .category(getCategory.orElse(category))
                .user(getUser.orElse(user))
                .createdAt(Instant.now())
                .build();

        log.info("Transaction object created --> {}", transaction);

        if(transaction.getAmount().compareTo(accountBalance) > 0) {
            log.info("Transaction amount is greater than balance --> {}", transaction.getAmount());
            assertThat(transaction.getAmount().compareTo(accountBalance) > 0).isFalse();
        } else {
            newAccountBalance = accountBalance.subtract(transaction.getAmount());
            getAccount.ifPresent(value -> value.setBalance(newAccountBalance));
            transactionRepository.save(transaction);
        }

        assertThat(transaction.getId()).isNotNull();
        log.info("Transaction object after saving --> {}", transaction);
    }

    @Test
    void findByUser() {
        Collection<Transaction> allTransactions = transactionRepository.findAll();

        assertThat(allTransactions.size()).isNotNull();
        log.info("Transaction table is not empty --> {}", allTransactions);

        user = userRepository.getOne((long)1);

        Collection<Transaction> transactions = transactionRepository.findByUser(user);
        assert transactions != null;
        log.info("Test to retrieve Transactions by user -> {}", transactions);
    }

    @Test
    void findTransactionByCategory() {
        Collection<Transaction> allTransactions = transactionRepository.findAll();

        assertThat(allTransactions.size()).isNotNull();
        log.info("Transaction table is not empty --> {}", allTransactions);

        category = categoryRepository.getOne((long)2);

        Optional<Transaction> transaction = transactionRepository.findTransactionByCategory(category);
        assert transaction.isPresent();
        log.info("Test to retrieve Transaction by Category -> {}", transaction);
    }

    @Test
    void findTransactionsByCategory() {
        Collection<Transaction> allTransactions = transactionRepository.findAll();

        assertThat(allTransactions.size()).isNotNull();
        log.info("Transaction table is not empty --> {}", allTransactions);

        category = categoryRepository.getOne((long)2);

        Collection<Transaction> transactions = transactionRepository.findTransactionsByCategory(category);
        assert transactions.size() == 1;
        log.info("Test to retrieve Transactions by Category -> {}", transactions);
    }

    @Test
    void findTransactionsByTransactionType() {
        Collection<Transaction> allTransactions = transactionRepository.findAll();

        assertThat(allTransactions.size()).isNotNull();
        log.info("Transaction table is not empty --> {}", allTransactions);

        Collection<Transaction> transactions = transactionRepository.findTransactionsByTransactionType(TransactionType.EXPENSE);
        assert transactions.size() == 1;
        log.info("Test to retrieve Transactions by Type -> {}", transactions);
    }

    @Test
    void findTransactionByTransactionType() {
        Collection<Transaction> allTransactions = transactionRepository.findAll();

        assertThat(allTransactions.size()).isNotNull();
        log.info("Transaction table is not empty --> {}", allTransactions);

        Optional<Transaction> transaction = transactionRepository.findTransactionByTransactionType(TransactionType.EXPENSE);
        assert transaction.isPresent();
        log.info("Test to retrieve Transaction by Type -> {}", transaction);
    }

    @Test
    void findAllTransactions() {
        Collection<Transaction> allTransactions = transactionRepository.findAll();
        assertThat(allTransactions.size()).isNotNull();
        log.info("Transaction table is not empty --> {}", allTransactions);
    }
}