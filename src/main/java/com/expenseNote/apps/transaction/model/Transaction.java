package com.expenseNote.apps.transaction.model;

import com.expenseNote.apps.account.model.Account;
import com.expenseNote.apps.category.model.Category;
import com.expenseNote.apps.user.model.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.time.Instant;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String title;

    @Nullable
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
    private Instant createdAt;
}
