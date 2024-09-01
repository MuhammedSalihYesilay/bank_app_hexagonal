package com.hexagonal.bank_app_hexagonal.domain.transaction.model;

import com.hexagonal.bank_app_hexagonal.domain.common.model.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Transaction {

    private String id;
    private String senderCustomerId;
    private String senderAccountId;
    private String receiverAccountId;
    private TransactionType transactionType;
    private BigDecimal amount;
    private LocalDateTime date;
    private String description;
}
