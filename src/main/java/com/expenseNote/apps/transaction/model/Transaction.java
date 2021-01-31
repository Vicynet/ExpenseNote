package com.expenseNote.apps.transaction.model;

import com.expenseNote.apps.account.model.Account;
import com.expenseNote.apps.category.model.Category;
import com.expenseNote.apps.user.model.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String title;

    @NotBlank
    @Size(max = 140)
    private String description;

    @NotBlank
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;

    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Account account;

    @NotBlank
    private BigInteger amount;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @NotBlank
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    @CreatedDate
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate modifiedAt;



    public Transaction() {
    }

    public Transaction(Long id, String title, String description, Category category, Account account, BigInteger amount, TransactionType transactionType, User user, LocalDate createdAt, LocalDate modifiedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.account = account;
        this.amount = amount;
        this.transactionType = transactionType;
        this.user = user;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
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

    public LocalDate getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDate modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", category=" + getCategory() +
                ", account=" + getAccount() +
                ", amount=" + getAmount() +
                ", transactionType=" + getTransactionType() +
                ", user=" + getUser() +
                ", createdAt=" + getCreatedAt() +
                ", modifiedAt=" + getModifiedAt() +
                '}';
    }
}
