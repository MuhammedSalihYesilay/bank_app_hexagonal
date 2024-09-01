package com.hexagonal.bank_app_hexagonal.infra.adapters.account.rest.dto;

import com.hexagonal.bank_app_hexagonal.domain.account.model.Account;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class AccountResponse {

    private String id;
    private String customerId;
    private String accountNumber;
    private BigDecimal balance;
    private LocalDateTime createdAt;

    public static AccountResponse from(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .customerId(account.getCustomerId())
                .balance(account.getBalance())
                .createdAt(account.getCreatedAt())
                .build();
    }
}
