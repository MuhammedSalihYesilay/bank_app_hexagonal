package com.hexagonal.bank_app_hexagonal.infra.adapters.transaction.rest;

import com.hexagonal.bank_app_hexagonal.domain.common.usecase.UseCaseHandler;
import com.hexagonal.bank_app_hexagonal.domain.transaction.model.Transaction;
import com.hexagonal.bank_app_hexagonal.domain.transaction.usercase.TransactionCreate;
import com.hexagonal.bank_app_hexagonal.domain.transaction.usercase.TransactionRetrieveAll;
import com.hexagonal.bank_app_hexagonal.infra.adapters.transaction.rest.dto.NewMoneyTransferRequest;
import com.hexagonal.bank_app_hexagonal.infra.adapters.transaction.rest.dto.TransactionResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("accounts")
public class TransactionController {

    private final UseCaseHandler<Transaction, TransactionCreate> transactionCreateUseCaseHandler;
    private final UseCaseHandler<List<Transaction>, TransactionRetrieveAll> transactionRetrieveAllUseCaseHandler;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("{accountId}/transfer-money")
    public TransactionResponse addNewTransaction(@PathVariable String accountId,
                                                        @RequestBody NewMoneyTransferRequest newMoneyTransferRequest,
                                                        UriComponentsBuilder ucb, HttpServletResponse response) {

        String customerId = getCustomerIdFromPrincipal();

        Transaction transaction = transactionCreateUseCaseHandler.handle(newMoneyTransferRequest.toUseCase(customerId));

        setTransactionLocationHeader(accountId, ucb, response, transaction);

        return TransactionResponse.from(transaction);
    }

    private static void setTransactionLocationHeader(String accountId, UriComponentsBuilder ucb, HttpServletResponse response, Transaction transaction) {
        URI locationOfNewTransaction = ucb
                .path("{accountId}/transfer-money/{transactionId}")
                .buildAndExpand(accountId, transaction.getId())
                .toUri();

        response.setHeader("Location", locationOfNewTransaction.toString());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{accountId}/transaction-history")
    public List<TransactionResponse> getAllTransactions(@PathVariable String accountId) {
        String customerId = getCustomerIdFromPrincipal();

        TransactionRetrieveAll useCase = new TransactionRetrieveAll(customerId, accountId);
        List<Transaction> transactions = transactionRetrieveAllUseCaseHandler.handle(useCase);

        return transactions.stream()
                .map(TransactionResponse::from)
                .collect(Collectors.toList());
    }

    private static String getCustomerIdFromPrincipal() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }
}