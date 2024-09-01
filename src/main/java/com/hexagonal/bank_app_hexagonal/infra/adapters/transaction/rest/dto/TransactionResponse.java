package com.hexagonal.bank_app_hexagonal.infra.adapters.transaction.rest.dto;


import com.hexagonal.bank_app_hexagonal.domain.common.model.TransactionType;
import com.hexagonal.bank_app_hexagonal.domain.transaction.model.Transaction;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionResponse {

    private String id;
    private String senderAccountId;
    private String receiverAccountId;
    private TransactionType transactionType;
    private BigDecimal amount;
    private LocalDateTime date;
    private String description;

    public static TransactionResponse from(Transaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .senderAccountId(transaction.getSenderAccountId())
                .receiverAccountId(transaction.getReceiverAccountId())
                .transactionType(transaction.getTransactionType())
                .amount(transaction.getAmount())
                .date(transaction.getDate())
                .description(transaction.getDescription())
                .build();
    }
}
