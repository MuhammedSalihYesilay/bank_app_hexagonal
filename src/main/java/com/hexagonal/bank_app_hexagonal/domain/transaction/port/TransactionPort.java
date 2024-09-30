package com.hexagonal.bank_app_hexagonal.domain.transaction.port;

import com.hexagonal.bank_app_hexagonal.domain.transaction.model.Transaction;
import com.hexagonal.bank_app_hexagonal.domain.transaction.usercase.TransactionCreate;

import java.util.List;


public interface TransactionPort {

    Transaction create(TransactionCreate transactionCreate);

    List<Transaction> getAllTransactions(String accountId, String customerId);
}
