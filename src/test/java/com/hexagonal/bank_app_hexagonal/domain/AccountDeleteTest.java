package com.hexagonal.bank_app_hexagonal.domain;

import com.hexagonal.bank_app_hexagonal.domain.account.AccountDeleteUseCaseHandler;
import com.hexagonal.bank_app_hexagonal.domain.account.port.AccountPort;
import com.hexagonal.bank_app_hexagonal.domain.account.usecase.AccountDelete;
import com.hexagonal.bank_app_hexagonal.domain.adapters.AccountFakeDataAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountDeleteTest {

    private AccountPort accountPort; // You can implement a fake version or a real implementation
    private AccountDeleteUseCaseHandler accountDeleteUseCaseHandler;

    @BeforeEach
    void setUp() {
        accountPort = new AccountFakeDataAdapter(); // Using the real or fake data adapter implementation
        accountDeleteUseCaseHandler = new AccountDeleteUseCaseHandler(accountPort);
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
