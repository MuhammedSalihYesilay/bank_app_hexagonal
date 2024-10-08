package com.hexagonal.bank_app_hexagonal.infra.adapters.account.jpa.entity;

import com.hexagonal.bank_app_hexagonal.domain.account.model.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class AccountEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "balance")
    private BigDecimal balance;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Account toModel(){
        return Account.builder()
                .id(id)
                .customerId(customerId)
                .accountNumber(accountNumber)
                .balance(balance)
                .createdAt(createdAt)
                .build();
    }
}
