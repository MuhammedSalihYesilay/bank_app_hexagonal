package com.hexagonal.bank_app_hexagonal.domain;

import com.hexagonal.bank_app_hexagonal.domain.account.AccountCreateUseCaseHandler;
import com.hexagonal.bank_app_hexagonal.domain.account.model.Account;
import com.hexagonal.bank_app_hexagonal.domain.account.port.AccountNumberGeneratorPort;
import com.hexagonal.bank_app_hexagonal.domain.account.usecase.AccountCreate;
import com.hexagonal.bank_app_hexagonal.domain.adapters.AccountFakeDataAdapter;
import com.hexagonal.bank_app_hexagonal.domain.transaction.port.TransactionPort;
import com.hexagonal.bank_app_hexagonal.domain.transaction.usercase.TransactionCreate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AccountCreateTest {

    AccountCreateUseCaseHandler accountCreateUseCaseHandler;
    AccountNumberGeneratorPort accountNumberGeneratorPort;
    TransactionPort transactionPort;

    @BeforeEach
    void setUp() {
        accountNumberGeneratorPort = mock(AccountNumberGeneratorPort.class);
        transactionPort = mock(TransactionPort.class);

        accountCreateUseCaseHandler = new AccountCreateUseCaseHandler(
                new AccountFakeDataAdapter(),
                transactionPort,
                accountNumberGeneratorPort
        );
    }

    @Test
    void should_create_account_with_initial_balance() {

        when(accountNumberGeneratorPort.generateAccountNumber()).thenReturn("9876543210987654");

        AccountCreate accountCreate = AccountCreate.builder()
                .customerId("customer-id")
                .balance(BigDecimal.valueOf(1000))
                .build();

        Account createdAccount = accountCreateUseCaseHandler.handle(accountCreate);

        // Assert
        assertNotNull(createdAccount);
        assertEquals("customer-id", createdAccount.getCustomerId());
        assertEquals(BigDecimal.valueOf(1000), createdAccount.getBalance());

        // Verify that initial transaction was created
        verify(transactionPort, times(1)).create(any(TransactionCreate.class));
    }

    @Test
    void should_create_account_with_zero_balance() {
        // Arrange
        when(accountNumberGeneratorPort.generateAccountNumber()).thenReturn("9876543210987654");

        AccountCreate accountCreate = AccountCreate.builder()
                .customerId("customer-id-2")
                .balance(BigDecimal.ZERO)
                .build();

        Account createdAccount = accountCreateUseCaseHandler.handle(accountCreate);

        // Assert
        assertNotNull(createdAccount);
        assertEquals("customer-id-2", createdAccount.getCustomerId());
        assertEquals(BigDecimal.ZERO, createdAccount.getBalance());

        // Verify that no initial transaction was created
        verify(transactionPort, never()).create(any(TransactionCreate.class));
    }
}