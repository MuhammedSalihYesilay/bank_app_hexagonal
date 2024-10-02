package com.hexagonal.bank_app_hexagonal.domain;

import com.hexagonal.bank_app_hexagonal.domain.account.AccountRetrieveAllUseCaseHandler;

import com.hexagonal.bank_app_hexagonal.domain.account.usecase.AccountRetrieveAll;
import com.hexagonal.bank_app_hexagonal.domain.adapters.AccountFakeDataAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

        String customerId1 = "customer id1";
        AccountRetrieveAll accountRetrieveAll1 = AccountRetrieveAll.builder()
                .customerId(customerId1)
                .build();

        var accounts = accountRetrieveAllUseCaseHandler.handle(accountRetrieveAll);
        var account = accountRetrieveAllUseCaseHandler.handle(accountRetrieveAll1);

        assertThat(accounts).isNotNull();
        assertThat(account).isNotNull();
        assertThat(accounts).isNotEmpty(); // Hesapların boş olmadığını kontrol edin
        assertThat(account).isNotEmpty(); // Hesapların boş olmadığını kontrol edin
        assertThat(accounts).hasSize(2); // Beklenen hesap sayısını kontrol edin
        assertEquals("customer id", accountRetrieveAll.getCustomerId());
        assertEquals("customer id1", accountRetrieveAll1.getCustomerId());
        assertThat(accounts.get(0).getAccountNumber()).isEqualTo("account number");
        assertThat(accounts.get(1).getAccountNumber()).isEqualTo("account number2");
        assertThat(accounts.get(0).getBalance()).isEqualTo(new BigDecimal("100.00"));
        assertThat(accounts.get(1).getBalance()).isEqualTo(new BigDecimal("200.00"));
    }
}
