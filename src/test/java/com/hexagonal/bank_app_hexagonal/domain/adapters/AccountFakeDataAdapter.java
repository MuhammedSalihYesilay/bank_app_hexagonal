package com.hexagonal.bank_app_hexagonal.domain.adapters;

import com.hexagonal.bank_app_hexagonal.domain.account.model.Account;
import com.hexagonal.bank_app_hexagonal.domain.account.port.AccountPort;
import com.hexagonal.bank_app_hexagonal.domain.account.usecase.AccountCreate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public class AccountFakeDataAdapter implements AccountPort {
    @Override
    public Account retrieve(String accountId, String customerId) {
        return Account.builder()
                .customerId("costumer id")
                .accountNumber("account number")
                .balance(new BigDecimal(1000))
                .createdAt(LocalDateTime.of(2002, Month.DECEMBER, 15, 0, 0))
                .build();
    }

    @Override
    public Account create(AccountCreate accountCreate, String generatedAccountNumber) {
        return Account.builder()
                .id("id")
                .customerId(accountCreate.getCustomerId())
                .accountNumber("account number")
                .balance(accountCreate.getBalance())
                .createdAt(LocalDateTime.of(2002, Month.DECEMBER, 15, 0, 0))
                .build();
    }

    @Override
    public Account retrieveByAccountNumber(String accountNumber) {
        if ("account number".equals(accountNumber)) {
            return Account.builder()
                    .id("1")
                    .customerId("customer id")
                    .accountNumber(accountNumber)
                    .balance(new BigDecimal("100.00"))
                    .createdAt(LocalDateTime.of(2002, Month.DECEMBER, 15, 0, 0))
                    .build();
        }
        return null;
    }

    @Override
    public List<Account> retrieveAll(String customerId) {
        return List.of(
                Account.builder()
                        .id("1")
                        .customerId("costumer id")
                        .accountNumber("account number")
                        .balance(new BigDecimal("100.00"))
                        .createdAt(LocalDateTime.of(2002, Month.DECEMBER, 15, 0, 0))
                        .build(),
                Account.builder()
                        .id("2")
                        .customerId("costumer id")
                        .accountNumber("account number2")
                        .balance(new BigDecimal("200.00"))
                        .createdAt(LocalDateTime.of(2002, Month.DECEMBER, 15, 0, 0))
                        .build()
        );
    }

    @Override
    public void delete(String accountId, String customerId) {

        System.out.println("Account with ID: " + accountId + " for customer: " + customerId + " deleted.");
    }

    @Override
    public void updateBalance(String accountId, BigDecimal amount) {

        System.out.println("Account balance updated for account ID: " + accountId + " with amount: " + amount);
    }
}
