package com.hexagonal.bank_app_hexagonal.domain.transaction.usercase;

import com.hexagonal.bank_app_hexagonal.domain.common.model.UseCase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TransactionRetrieveAll implements UseCase {

    private String senderCustomerId;
    private String senderAccountId;
}
