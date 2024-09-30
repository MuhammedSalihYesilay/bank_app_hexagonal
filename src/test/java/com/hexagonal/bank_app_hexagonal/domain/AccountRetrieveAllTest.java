package com.hexagonal.bank_app_hexagonal.domain;

import com.hexagonal.bank_app_hexagonal.domain.account.AccountRetrieveAllUseCaseHandler;

import com.hexagonal.bank_app_hexagonal.domain.account.usecase.AccountRetrieveAll;
import com.hexagonal.bank_app_hexagonal.domain.adapters.AccountFakeDataAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountRetrieveAllTest {

    AccountRetrieveAllUseCaseHandler accountRetrieveAllUseCaseHandler;

    @BeforeEach
    void setUp() {
        accountRetrieveAllUseCaseHandler = new AccountRetrieveAllUseCaseHandler(new AccountFakeDataAdapter());
    }

    @Test
    void should_retrieveAll_account(){
        String customerId = "customer id";
        AccountRetrieveAll accountRetrieveAll = AccountRetrieveAll.builder()
                .customerId(customerId)
                .build();

        var accounts = accountRetrieveAllUseCaseHandler.handle(accountRetrieveAll);

        assertThat(accounts).isNotNull();
        assertThat(accounts).isNotEmpty(); // Hesapların boş olmadığını kontrol edin
        assertThat(accounts).hasSize(2); // Beklenen hesap sayısını kontrol edin
        assertThat(accounts.get(0).getBalance()).isEqualTo(new BigDecimal("100.00"));
        assertThat(accounts.get(1).getBalance()).isEqualTo(new BigDecimal("200.00"));
    }
}
