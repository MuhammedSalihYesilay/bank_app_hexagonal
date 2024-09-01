package com.hexagonal.bank_app_hexagonal.domain.account;

import com.hexagonal.bank_app_hexagonal.domain.account.model.Account;
import com.hexagonal.bank_app_hexagonal.domain.account.port.AccountNumberGeneratorPort;
import com.hexagonal.bank_app_hexagonal.domain.account.port.AccountPort;
import com.hexagonal.bank_app_hexagonal.domain.account.usecase.AccountCreate;
import com.hexagonal.bank_app_hexagonal.domain.common.DomainComponent;
import com.hexagonal.bank_app_hexagonal.domain.common.usecase.UseCaseHandler;
import com.hexagonal.bank_app_hexagonal.domain.transaction.port.TransactionPort;
import com.hexagonal.bank_app_hexagonal.domain.common.model.TransactionType;
import com.hexagonal.bank_app_hexagonal.domain.transaction.usercase.TransactionCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class AccountCreateUseCaseHandler implements UseCaseHandler<Account, AccountCreate> {

    private final AccountPort accountPort;
    private final TransactionPort transactionPort;
    private final AccountNumberGeneratorPort accountNumberGeneratorPort;

    @Override
    @Transactional
    public Account handle(AccountCreate useCase) {
        log.info("Handling account create : {}", useCase);

        String generatedAccountNumber = accountNumberGeneratorPort.generateAccountNumber();

        Account createdAccount = accountPort.create(useCase, generatedAccountNumber);

        if (useCase.isInitialBalanceBiggerThanZero()) {
            createInitialTransaction(createdAccount);
        }

        return createdAccount;
    }

    private void createInitialTransaction(Account account) {
        var transactionCreate = TransactionCreate.builder()
                .senderAccountId(account.getId())
                .receiverAccountNumber(account.getId())
                .transactionType(TransactionType.INITIAL)
                .amount(account.getBalance())
                .description("Initial deposit")
                .build();

        transactionPort.create(transactionCreate);
    }
}
