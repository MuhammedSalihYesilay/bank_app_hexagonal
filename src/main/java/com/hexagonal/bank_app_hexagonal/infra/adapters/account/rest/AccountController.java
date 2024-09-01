package com.hexagonal.bank_app_hexagonal.infra.adapters.account.rest;

import com.hexagonal.bank_app_hexagonal.domain.account.model.Account;
import com.hexagonal.bank_app_hexagonal.domain.account.usecase.AccountCreate;
import com.hexagonal.bank_app_hexagonal.domain.account.usecase.AccountDelete;
import com.hexagonal.bank_app_hexagonal.domain.account.usecase.AccountRetrieve;
import com.hexagonal.bank_app_hexagonal.domain.account.usecase.AccountRetrieveAll;
import com.hexagonal.bank_app_hexagonal.domain.common.usecase.UseCaseHandler;
import com.hexagonal.bank_app_hexagonal.domain.common.usecase.VoidUseCaseHandler;
import com.hexagonal.bank_app_hexagonal.infra.adapters.account.rest.dto.NewAccountRequest;
import com.hexagonal.bank_app_hexagonal.infra.adapters.account.rest.dto.AccountResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("accounts")
public class AccountController {

    private final UseCaseHandler<Account, AccountCreate> accountCreateUseCaseHandler;
    private final UseCaseHandler<List<Account>, AccountRetrieveAll> accountRetrieveAllUseCaseHandler;
    private final UseCaseHandler<Account, AccountRetrieve> accountRetrieveUseCaseHandler;
    private final VoidUseCaseHandler<AccountDelete> accountDeleteUseCaseHandler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse addNewAccount(@RequestBody NewAccountRequest newAccountRequest,
                                                UriComponentsBuilder ucb,
                                                HttpServletResponse response) {

        String customerId = getCustomerIdFromPrincipal();

        Account account = accountCreateUseCaseHandler.handle(newAccountRequest.toUseCase(customerId));

        setAccountLocationHeader(ucb, response, account);

        return AccountResponse.from(account);
    }

    private static void setAccountLocationHeader(UriComponentsBuilder ucb, HttpServletResponse response, Account account) {
        URI locationOfNewAccount = ucb
                .path("/accounts/{id}")
                .buildAndExpand(account.getId())
                .toUri();

        response.setHeader("Location", locationOfNewAccount.toString());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AccountResponse> getAllAccounts() {
        String customerId = getCustomerIdFromPrincipal();
        AccountRetrieveAll useCase = new AccountRetrieveAll(customerId);
        List<Account> accounts = accountRetrieveAllUseCaseHandler.handle(useCase);
        return accounts.stream().map(AccountResponse::from).toList();
    }

    @GetMapping("{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountResponse getAccountByIdCustomerId(@PathVariable String accountId) {
        String customerId = getCustomerIdFromPrincipal();
        AccountRetrieve useCase = new AccountRetrieve(customerId, accountId);
        Account account = accountRetrieveUseCaseHandler.handle(useCase);
        return AccountResponse.from(account);
    }

    @DeleteMapping("{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountById(@PathVariable String accountId) {
        String customerId = getCustomerIdFromPrincipal();
        AccountDelete useCase = new AccountDelete(customerId, accountId);
        accountDeleteUseCaseHandler.handle(useCase);
    }

    private static String getCustomerIdFromPrincipal() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }
}
