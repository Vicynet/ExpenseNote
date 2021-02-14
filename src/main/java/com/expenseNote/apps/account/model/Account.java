package com.expenseNote.apps.account.model;

import com.expenseNote.apps.user.model.User;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.time.Instant;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @NotBlank
    @Size(min = 10, max = 10)
    private String accountNumber;

    @NotNull
    @Value("0.00")
    private BigInteger balance;

    @NotBlank
    @CreatedBy
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    @NotBlank
    @CreatedDate
    private Instant createdAt;

}
