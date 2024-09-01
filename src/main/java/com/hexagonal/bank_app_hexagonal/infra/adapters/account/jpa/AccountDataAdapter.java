package com.hexagonal.bank_app_hexagonal.infra.adapters.account.jpa;

import com.hexagonal.bank_app_hexagonal.domain.account.exception.AccountNotFoundException;
import com.hexagonal.bank_app_hexagonal.domain.account.model.Account;
import com.hexagonal.bank_app_hexagonal.domain.account.port.AccountPort;
import com.hexagonal.bank_app_hexagonal.domain.account.usecase.AccountCreate;
import com.hexagonal.bank_app_hexagonal.infra.adapters.account.jpa.entity.AccountEntity;
import com.hexagonal.bank_app_hexagonal.infra.adapters.account.jpa.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountDataAdapter implements AccountPort {

    private final AccountRepository accountRepository;

    @Override
    public Account create(AccountCreate accountCreate, String generatedAccountNumber) {
        AccountEntity accountEntity = AccountEntity.builder()
                .customerId(accountCreate.getCustomerId())
                .accountNumber(generatedAccountNumber)
                .balance(accountCreate.getBalance())
                .build();

        AccountEntity savedAccountEntity = accountRepository.save(accountEntity);

        return savedAccountEntity.toModel();
    }

    @Override
    public Account retrieve(String accountId, String customerId) {
        return accountRepository.findByIdAndCustomerId(accountId, customerId)
                .map(AccountEntity::toModel)
                .orElseThrow(() -> new AccountNotFoundException("Account ID: " + accountId + ", Customer ID: " + customerId));
    }

    @Override
    public Account retrieveByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .map(AccountEntity::toModel)
                .orElseThrow(() -> new AccountNotFoundException("Account Number: " + accountNumber));
    }


    @Override
    public List<Account> retrieveAll(String customerId) {
        return accountRepository.findAllByCustomerId(customerId)
                .stream().map(AccountEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String accountId, String customerId) {
        AccountEntity accountEntity = accountRepository.findByIdAndCustomerId(accountId, customerId)
                .orElseThrow(() -> new AccountNotFoundException("Account ID: " + accountId + ", Customer ID: " + customerId));
        accountRepository.delete(accountEntity);
    }

    @Override
    public void updateBalance(String accountId, BigDecimal amount) {
        AccountEntity accountEntity = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account ID: " + accountId));
        accountEntity.setBalance(accountEntity.getBalance().add(amount));
        accountRepository.save(accountEntity);
    }
}
