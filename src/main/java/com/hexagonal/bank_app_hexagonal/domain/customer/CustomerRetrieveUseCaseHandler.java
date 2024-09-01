package com.hexagonal.bank_app_hexagonal.domain.customer;

import com.hexagonal.bank_app_hexagonal.domain.common.DomainComponent;
import com.hexagonal.bank_app_hexagonal.domain.common.usecase.UseCaseHandler;
import com.hexagonal.bank_app_hexagonal.domain.customer.model.Customer;
import com.hexagonal.bank_app_hexagonal.domain.customer.port.CustomerPort;
import com.hexagonal.bank_app_hexagonal.domain.customer.usecase.CustomerRetrieve;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class CustomerRetrieveUseCaseHandler implements UseCaseHandler<Customer, CustomerRetrieve>{

    private final CustomerPort customerPort;

    @Override
    public Customer handle(CustomerRetrieve useCase) {
       return customerPort.retrieve(useCase.getEmail());
    }
}
