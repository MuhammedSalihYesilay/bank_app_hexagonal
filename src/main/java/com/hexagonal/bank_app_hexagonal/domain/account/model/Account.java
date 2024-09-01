package com.hexagonal.bank_app_hexagonal.domain.account.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Account {

    private String id;
    private String customerId;
    private String accountNumber;
    private BigDecimal balance;
    private LocalDateTime createdAt;

    public boolean isBalanceSufficient(BigDecimal transactionAmount) {
        return balance.compareTo(transactionAmount) >= 0;
    }

    public boolean isBalanceInsufficient(BigDecimal transactionAmount) {
        return !isBalanceSufficient(transactionAmount);
    }
}
