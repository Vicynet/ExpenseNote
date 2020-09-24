package com.expenseNote.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @NotBlank
    @Size(min = 10, max = 10)
    private Long accountNumber;

    @NotNull
    @Value("0.00")
    private BigInteger balance;

    @NotBlank
    @CreatedBy
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    @NotBlank
    @CreatedDate
    private LocalDate createdAt;

    public Account() {
    }

    public Account(Long id, AccountType accountType, Long accountNumber, BigInteger balance, User user, LocalDate createdAt) {
        this.id = id;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.user = user;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String
    toString() {
        return "Account{" +
                "id=" + getId() +
                ", accountType=" + getAccountType() +
                ", accountNumber=" + getAccountNumber() +
                ", balance=" + getBalance() +
                ", user=" + getUser() +
                ", createdAt=" + getCreatedAt() +
                '}';
    }
}
