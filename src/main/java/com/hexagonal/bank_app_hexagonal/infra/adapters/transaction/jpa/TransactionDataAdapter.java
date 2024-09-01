package com.hexagonal.bank_app_hexagonal.infra.adapters.transaction.jpa;

import com.hexagonal.bank_app_hexagonal.domain.account.exception.AccountNotFoundException;
import com.hexagonal.bank_app_hexagonal.domain.transaction.model.Transaction;
import com.hexagonal.bank_app_hexagonal.domain.transaction.port.TransactionPort;
import com.hexagonal.bank_app_hexagonal.domain.transaction.usercase.TransactionCreate;
import com.hexagonal.bank_app_hexagonal.infra.adapters.account.jpa.repository.AccountRepository;
import com.hexagonal.bank_app_hexagonal.infra.adapters.transaction.jpa.entity.TransactionEntity;
import com.hexagonal.bank_app_hexagonal.infra.adapters.transaction.jpa.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionDataAdapter implements TransactionPort {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Override
    public Transaction create(TransactionCreate transactionCreate) {
        TransactionEntity transactionEntity = TransactionEntity.builder()
                .amount(transactionCreate.getAmount())
                .receiverAccountId(transactionCreate.getReceiverAccountNumber())
                .senderAccountId(transactionCreate.getSenderAccountId())
                .transactionType(transactionCreate.getTransactionType())
                .description(transactionCreate.getDescription())
                .build();
        TransactionEntity savedTransactionEntity = transactionRepository.save(transactionEntity);
        return savedTransactionEntity.toModel();
    }

    @Override
    public List<Transaction> getAllTransactions(String accountId, String customerId){
        accountRepository.findByIdAndCustomerId(accountId, customerId)
                .orElseThrow(() -> new AccountNotFoundException("Account ID: " + accountId + ", Customer ID: " + customerId));
        List<TransactionEntity> transactions = transactionRepository.findAllByAccountId(accountId);
        return transactions.stream()
                .map(TransactionEntity::toModel)
                .collect(Collectors.toList());
    }
}
