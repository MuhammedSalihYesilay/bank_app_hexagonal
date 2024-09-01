package com.hexagonal.bank_app_hexagonal.domain.account.port;

import com.hexagonal.bank_app_hexagonal.domain.account.model.Account;
import com.hexagonal.bank_app_hexagonal.domain.account.usecase.AccountCreate;

import java.math.BigDecimal;
import java.util.List;

public interface AccountPort {

    Account retrieve(String accountId, String customerId);

    Account create(AccountCreate accountCreate, String generatedAccountNumber);

    Account retrieveByAccountNumber(String accountNumber);

    List<Account> retrieveAll(String customerId);

    void delete(String accountId, String customerId);

    void updateBalance(String accountId, BigDecimal amount);
}
