package com.hexagonal.bank_app_hexagonal.domain.account;

import com.hexagonal.bank_app_hexagonal.domain.account.port.AccountPort;
import com.hexagonal.bank_app_hexagonal.domain.account.usecase.AccountDelete;
import com.hexagonal.bank_app_hexagonal.domain.common.DomainComponent;
import com.hexagonal.bank_app_hexagonal.domain.common.usecase.VoidUseCaseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class AccountDeleteUseCaseHandler implements VoidUseCaseHandler<AccountDelete> {

    private final AccountPort accountPort;

    @Override
    public void handle(AccountDelete useCase) {
        accountPort.delete(useCase.getAccountId(), useCase.getCustomerId());
    }

}
