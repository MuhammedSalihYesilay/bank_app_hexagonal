package com.hexagonal.bank_app_hexagonal.domain;

import com.hexagonal.bank_app_hexagonal.domain.account.AccountDeleteUseCaseHandler;
import com.hexagonal.bank_app_hexagonal.domain.account.usecase.AccountDelete;
import com.hexagonal.bank_app_hexagonal.domain.adapters.AccountFakeDataAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountDeleteTest {

    AccountDeleteUseCaseHandler accountDeleteUseCaseHandler;

    @BeforeEach
    void setUp() {
        accountDeleteUseCaseHandler = new AccountDeleteUseCaseHandler(new AccountFakeDataAdapter());
    }

    @Test
    void should_delete_account() {
        // Given
        String accountId = "accountId";
        String customerId = "customerId";
        AccountDelete accountDelete = new AccountDelete(accountId, customerId);

        // When
        accountDeleteUseCaseHandler.handle(accountDelete);
    }
}
