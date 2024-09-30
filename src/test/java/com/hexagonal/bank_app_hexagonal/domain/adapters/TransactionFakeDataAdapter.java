package com.hexagonal.bank_app_hexagonal.domain.adapters;

import com.hexagonal.bank_app_hexagonal.domain.common.model.TransactionType;
import com.hexagonal.bank_app_hexagonal.domain.transaction.model.Transaction;
import com.hexagonal.bank_app_hexagonal.domain.transaction.port.TransactionPort;
import com.hexagonal.bank_app_hexagonal.domain.transaction.usercase.TransactionCreate;

import java.math.BigDecimal;
import java.util.List;

public class TransactionFakeDataAdapter implements TransactionPort {
    @Override
    public Transaction create(TransactionCreate transactionCreate) {
        return Transaction.builder()
                .id("id")
                .senderCustomerId(transactionCreate.getSenderCustomerId())
                .senderAccountId(transactionCreate.getSenderAccountId())
                .receiverAccountId(transactionCreate.getReceiverAccountNumber())
                .transactionType(transactionCreate.getTransactionType())
                .amount(transactionCreate.getAmount())
                .description(transactionCreate.getDescription())
                .build();
    }

    @Override
    public List<Transaction> getAllTransactions(String accountId, String customerId) {
        return List.of(
                Transaction.builder()
                        .id("id")
                        .senderCustomerId("sender costumer id")
                        .senderAccountId("sender account id")
                        .receiverAccountId("receiver account id")
                        .transactionType(TransactionType.TRANSFER)
                        .amount(BigDecimal.valueOf(1000))
                        .description("description")
                        .build(),
                Transaction.builder()
                        .id("id1")
                        .senderCustomerId("sender costumer id1")
                        .senderAccountId("sender account id1")
                        .receiverAccountId("receiver account id1")
                        .transactionType(TransactionType.TRANSFER)
                        .amount(BigDecimal.valueOf(1000))
                        .description("description")
                        .build()
        );
    }
}
