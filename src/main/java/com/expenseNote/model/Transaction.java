package com.expenseNote.model;

import org.springframework.data.annotation.CreatedDate;

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
    private Boolean isExpense = true;

    @NotBlank
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    @CreatedDate
    private LocalDate createdAt;

    public Transaction() {
    }

    public Transaction(Long id, String title, String description, Category category, Account account, BigInteger amount, Boolean isExpense, User user, LocalDate createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.account = account;
        this.amount = amount;
        this.isExpense = isExpense;
        this.user = user;
        this.createdAt = createdAt;
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

    public Boolean getIsExpense() {
        return isExpense;
    }

    public void setIsExpense(Boolean expense) {
        isExpense = expense;
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
        return "Expense{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", account=" + account +
                ", amount=" + amount +
                ", isExpense=" + getIsExpense() +
                ", user=" + user +
                ", createdAt=" + createdAt +
                '}';
    }
}
