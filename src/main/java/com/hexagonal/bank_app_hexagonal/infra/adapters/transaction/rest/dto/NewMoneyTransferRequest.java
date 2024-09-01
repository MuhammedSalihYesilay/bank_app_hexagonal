package com.hexagonal.bank_app_hexagonal.infra.adapters.transaction.rest.dto;

import com.hexagonal.bank_app_hexagonal.domain.common.model.TransactionType;
import com.hexagonal.bank_app_hexagonal.domain.transaction.usercase.TransactionCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewMoneyTransferRequest {

    @NotBlank
    private String senderAccountId;

    @NotBlank
    private String receiverAccountId;

    @NotBlank
    private TransactionType transactionType;

    @NotBlank
    @Positive
    private BigDecimal amount;

    @NotBlank
    private String description;

    public TransactionCreate toUseCase(String customerId) {
        return TransactionCreate.builder()
                .senderCustomerId(customerId)
                .senderAccountId(senderAccountId)
                .receiverAccountNumber(receiverAccountId)
                .transactionType(transactionType)
                .amount(amount)
                .description(description)
                .build();
    }
}
