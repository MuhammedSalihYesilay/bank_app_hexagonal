package com.hexagonal.bank_app_hexagonal.domain;

import com.hexagonal.bank_app_hexagonal.domain.account.AccountRetrieveUseCaseHandler;
import com.hexagonal.bank_app_hexagonal.domain.account.usecase.AccountRetrieve;
import com.hexagonal.bank_app_hexagonal.domain.adapters.AccountFakeDataAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountRetrieveTest {

    AccountRetrieveUseCaseHandler accountRetrieveUseCaseHandler;

    @BeforeEach
    void setUp() {
        accountRetrieveUseCaseHandler = new AccountRetrieveUseCaseHandler(new AccountFakeDataAdapter());
    }

    @Test
    void should_retrieve_account(){
        AccountRetrieve accountRetrieve = AccountRetrieve.builder()
                .accountId("account number")
                .customerId("costumer id")
                .build();

        var account = accountRetrieveUseCaseHandler.handle( accountRetrieve);

        assertThat(account).isNotNull();
        assertThat(account).isNotNull();
        assertThat(account.getAccountNumber()).isEqualTo("account number");
        assertThat(account.getBalance()).isEqualTo(new BigDecimal(1000));

    }
}
