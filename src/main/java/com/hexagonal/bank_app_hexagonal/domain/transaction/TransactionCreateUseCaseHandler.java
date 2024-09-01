package com.hexagonal.bank_app_hexagonal.domain.transaction;

import com.hexagonal.bank_app_hexagonal.domain.account.model.Account;
import com.hexagonal.bank_app_hexagonal.domain.account.port.AccountPort;
import com.hexagonal.bank_app_hexagonal.domain.common.DomainComponent;
import com.hexagonal.bank_app_hexagonal.domain.common.usecase.UseCaseHandler;
import com.hexagonal.bank_app_hexagonal.domain.transaction.exception.InsufficientBalanceException;
import com.hexagonal.bank_app_hexagonal.domain.transaction.model.Transaction;
import com.hexagonal.bank_app_hexagonal.domain.transaction.port.TransactionPort;
import com.hexagonal.bank_app_hexagonal.domain.transaction.usercase.TransactionCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class TransactionCreateUseCaseHandler implements UseCaseHandler<Transaction, TransactionCreate> {

    private final TransactionPort transactionPort;
    private final AccountPort accountPort;

    @Override
    @Transactional
    public Transaction handle(TransactionCreate useCase) {
        var senderAccount = accountPort.retrieve(useCase.getSenderAccountId(), useCase.getSenderCustomerId());

        validateSenderBalance(useCase.getAmount(), senderAccount);

        var receiverAccount = accountPort.retrieveByAccountNumber(useCase.getReceiverAccountNumber());

        updateBalances(senderAccount.getId(), receiverAccount.getId(), useCase.getAmount());

        return transactionPort.create(useCase);
    }

    private void validateSenderBalance(BigDecimal transactionAmount, Account senderAccount) {
        if(senderAccount.isBalanceInsufficient(transactionAmount)) {
            throw new InsufficientBalanceException();
        }
    }

    private void updateBalances(String senderAccountId, String receiverAccountId, BigDecimal amount) {
        accountPort.updateBalance(senderAccountId, amount.negate());
        accountPort.updateBalance(receiverAccountId, amount);
    }
}
