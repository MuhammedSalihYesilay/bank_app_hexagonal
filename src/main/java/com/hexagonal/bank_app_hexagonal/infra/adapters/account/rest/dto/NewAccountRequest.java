package com.hexagonal.bank_app_hexagonal.infra.adapters.account.rest.dto;


import com.hexagonal.bank_app_hexagonal.domain.account.usecase.AccountCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewAccountRequest {

    @NotBlank
    @PositiveOrZero
    private BigDecimal initialBalance;

    public AccountCreate toUseCase(String customerId) {
        return AccountCreate.builder()
                .balance(initialBalance)
                .customerId(customerId)
                .build();
    }
}