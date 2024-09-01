package com.hexagonal.bank_app_hexagonal.domain.transaction;

import com.hexagonal.bank_app_hexagonal.domain.account.port.AccountPort;
import com.hexagonal.bank_app_hexagonal.domain.common.DomainComponent;
import com.hexagonal.bank_app_hexagonal.domain.common.usecase.UseCaseHandler;
import com.hexagonal.bank_app_hexagonal.domain.transaction.model.Transaction;
import com.hexagonal.bank_app_hexagonal.domain.transaction.port.TransactionPort;
import com.hexagonal.bank_app_hexagonal.domain.transaction.usercase.TransactionRetrieveAll;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class TransactionRetrieveAllUseCaseHandler implements UseCaseHandler<List<Transaction>, TransactionRetrieveAll> {

    private final TransactionPort transactionPort;

    @Override
    public List<Transaction> handle(TransactionRetrieveAll useCase) {
        return transactionPort.getAllTransactions(useCase.getSenderAccountId(), useCase.getSenderCustomerId());
    }
}
