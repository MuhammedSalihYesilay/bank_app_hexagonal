package com.hexagonal.bank_app_hexagonal.domain.account;

import com.hexagonal.bank_app_hexagonal.domain.account.model.Account;
import com.hexagonal.bank_app_hexagonal.domain.account.port.AccountPort;
import com.hexagonal.bank_app_hexagonal.domain.account.usecase.AccountRetrieve;
import com.hexagonal.bank_app_hexagonal.domain.common.DomainComponent;
import com.hexagonal.bank_app_hexagonal.domain.common.usecase.UseCaseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class AccountRetrieveUseCaseHandler implements UseCaseHandler<Account, AccountRetrieve> {

    private final AccountPort accountPort;

    @Override
    public Account handle(AccountRetrieve useCase) {
        return accountPort.retrieve(useCase.getAccountId(), useCase.getCustomerId());
    }
}
