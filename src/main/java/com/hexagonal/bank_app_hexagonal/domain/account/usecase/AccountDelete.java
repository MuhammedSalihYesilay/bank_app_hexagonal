package com.hexagonal.bank_app_hexagonal.domain.account.usecase;

import com.hexagonal.bank_app_hexagonal.domain.common.model.UseCase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AccountDelete implements UseCase {

    private String customerId;
    private String accountId;
}
