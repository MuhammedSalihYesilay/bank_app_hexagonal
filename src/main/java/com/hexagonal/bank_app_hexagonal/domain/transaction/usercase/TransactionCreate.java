package com.hexagonal.bank_app_hexagonal.domain.transaction.usercase;

import com.hexagonal.bank_app_hexagonal.domain.common.model.UseCase;
import com.hexagonal.bank_app_hexagonal.domain.common.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class TransactionCreate implements UseCase {

    private String senderCustomerId;
    private String senderAccountId;
    private String receiverAccountNumber;
    private TransactionType transactionType;
    private BigDecimal amount;
    private String description;
}
