package com.hexagonal.bank_app_hexagonal.domain.account.usecase;

import com.hexagonal.bank_app_hexagonal.domain.common.model.UseCase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class AccountCreate implements UseCase {

    private String customerId;
    private BigDecimal balance;

    public boolean isInitialBalanceBiggerThanZero() {
        return balance.compareTo(BigDecimal.ZERO) > 0;
    }
}
