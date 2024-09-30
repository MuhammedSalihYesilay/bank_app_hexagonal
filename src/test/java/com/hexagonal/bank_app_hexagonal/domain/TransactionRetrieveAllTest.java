package com.hexagonal.bank_app_hexagonal.domain;

import com.hexagonal.bank_app_hexagonal.domain.adapters.TransactionFakeDataAdapter;
import com.hexagonal.bank_app_hexagonal.domain.transaction.TransactionRetrieveAllUseCaseHandler;
import com.hexagonal.bank_app_hexagonal.domain.transaction.model.Transaction;
import com.hexagonal.bank_app_hexagonal.domain.transaction.usercase.TransactionRetrieveAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionRetrieveAllTest {

    TransactionRetrieveAllUseCaseHandler transactionRetrieveAllUseCaseHandler;

    @BeforeEach
    void setUp() {
        transactionRetrieveAllUseCaseHandler = new TransactionRetrieveAllUseCaseHandler(new TransactionFakeDataAdapter());
    }

    @Test
    void should_retrieveAll_transactions() {
        TransactionRetrieveAll transactionRetrieveAll = TransactionRetrieveAll.builder()
                .senderAccountId("sender account id")
                .senderCustomerId("sender customer id")
                .build();

        List<Transaction> transactions = transactionRetrieveAllUseCaseHandler.handle(transactionRetrieveAll);

        assertThat(transactions).isNotNull().hasSize(2);
        assertThat(transactions.get(0)).satisfies(transaction -> {
            assertThat(transaction.getSenderAccountId()).isEqualTo("sender account id");
            assertThat(transaction.getSenderCustomerId()).isEqualTo("sender costumer id");
        });
    }
}
