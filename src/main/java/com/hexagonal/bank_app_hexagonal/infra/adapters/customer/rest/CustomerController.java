package com.hexagonal.bank_app_hexagonal.infra.adapters.customer.rest;

import com.hexagonal.bank_app_hexagonal.domain.common.usecase.UseCaseHandler;
import com.hexagonal.bank_app_hexagonal.domain.customer.model.Customer;
import com.hexagonal.bank_app_hexagonal.domain.customer.usecase.CustomerRegister;
import com.hexagonal.bank_app_hexagonal.domain.customer.usecase.CustomerRetrieve;
import com.hexagonal.bank_app_hexagonal.infra.adapters.customer.rest.dto.CustomerResponse;
import com.hexagonal.bank_app_hexagonal.infra.adapters.customer.rest.dto.CustomerRegisterRequest;
import com.hexagonal.bank_app_hexagonal.infra.adapters.customer.rest.dto.RegisterResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class CustomerController {

    private final UseCaseHandler<Customer, CustomerRegister> customerRegisterUseCaseHandler;
    private final UseCaseHandler<Customer, CustomerRetrieve> customerRetrieveUseCaseHandler;

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse registerCustomer(@RequestBody CustomerRegisterRequest customerRegisterRequest) {
        customerRegisterUseCaseHandler.handle(CustomerRegisterRequest.toCustomerRegister(customerRegisterRequest));
        return new RegisterResponse();
    }

    @GetMapping("me")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getAuthenticatedCustomer() {
        String email = getCustomerEmailFromPrincipal();
        Customer customer = customerRetrieveUseCaseHandler.handle(new CustomerRetrieve(email));
        return CustomerResponse.from(customer);
    }

    private static String getCustomerEmailFromPrincipal() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }
}
