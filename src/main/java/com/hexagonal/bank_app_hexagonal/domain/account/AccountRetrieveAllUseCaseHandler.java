package com.hexagonal.bank_app_hexagonal.domain.account;

import com.hexagonal.bank_app_hexagonal.domain.account.model.Account;
import com.hexagonal.bank_app_hexagonal.domain.account.port.AccountPort;
import com.hexagonal.bank_app_hexagonal.domain.account.usecase.AccountRetrieveAll;
import com.hexagonal.bank_app_hexagonal.domain.common.DomainComponent;
import com.hexagonal.bank_app_hexagonal.domain.common.usecase.UseCaseHandler;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class AccountRetrieveAllUseCaseHandler implements UseCaseHandler<List<Account>, AccountRetrieveAll> {

    private final AccountPort accountPort;

    @Override
    public List<Account> handle(AccountRetrieveAll useCase) {
        return accountPort.retrieveAll(useCase.getCustomerId());
    }
}