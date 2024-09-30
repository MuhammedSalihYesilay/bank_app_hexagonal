package com.hexagonal.bank_app_hexagonal.domain;


import com.hexagonal.bank_app_hexagonal.domain.account.model.Account;
import com.hexagonal.bank_app_hexagonal.domain.account.port.AccountPort;
import com.hexagonal.bank_app_hexagonal.domain.adapters.TransactionFakeDataAdapter;
import com.hexagonal.bank_app_hexagonal.domain.common.model.TransactionType;
import com.hexagonal.bank_app_hexagonal.domain.transaction.TransactionCreateUseCaseHandler;
import com.hexagonal.bank_app_hexagonal.domain.transaction.model.Transaction;
import com.hexagonal.bank_app_hexagonal.domain.transaction.usercase.TransactionCreate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class TransactionCreateTest {

    TransactionCreateUseCaseHandler transactionCreateUseCaseHandler;
    AccountPort accountPort;

    @BeforeEach
    void setUp(){
        accountPort = mock(AccountPort.class);
        transactionCreateUseCaseHandler = new TransactionCreateUseCaseHandler(
                new TransactionFakeDataAdapter(),
                accountPort
        );
    }

    @Test
    void should_create_transaction() {
        // Given
        TransactionCreate transactionCreate = TransactionCreate.builder()
                .senderCustomerId("test sender customer id")
                .senderAccountId("test sender account id")
                .receiverAccountNumber("test receiver account number")
                .transactionType(TransactionType.TRANSFER)
                .amount(BigDecimal.valueOf(1000))
                .description("test description")
                .build();

        Account senderAccount = mock(Account.class);
        Account receiverAccount = mock(Account.class);

        when(accountPort.retrieve(anyString(), anyString())).thenReturn(senderAccount);
        when(accountPort.retrieveByAccountNumber(anyString())).thenReturn(receiverAccount);
        when(senderAccount.isBalanceInsufficient(any())).thenReturn(false);
        when(senderAccount.getId()).thenReturn("test sender account id");
        when(receiverAccount.getId()).thenReturn("test receiver account id");

        // When
        Transaction createdTransaction = transactionCreateUseCaseHandler.handle(transactionCreate);

        // Then
        assertThat(createdTransaction).isNotNull()
                .satisfies(transaction -> {
                    assertThat(transaction.getId()).isEqualTo("id");
                    assertThat(transaction.getSenderCustomerId()).isEqualTo("test sender customer id");
                    assertThat(transaction.getSenderAccountId()).isEqualTo("test sender account id");
                    assertThat(transaction.getReceiverAccountId()).isEqualTo("test receiver account number");
                    assertThat(transaction.getDescription()).isEqualTo("test description");
                    assertThat(transaction.getTransactionType()).isEqualTo(TransactionType.TRANSFER);
                    assertThat(transaction.getAmount()).isEqualTo(BigDecimal.valueOf(1000));
                });

        verify(accountPort).updateBalance(eq("test sender account id"), eq(BigDecimal.valueOf(-1000)));
        verify(accountPort).updateBalance(eq("test receiver account id"), eq(BigDecimal.valueOf(1000)));
    }
}