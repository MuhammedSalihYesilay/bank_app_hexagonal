package com.hexagonal.bank_app_hexagonal.domain.customer;

import com.hexagonal.bank_app_hexagonal.domain.common.DomainComponent;
import com.hexagonal.bank_app_hexagonal.domain.common.usecase.UseCaseHandler;
import com.hexagonal.bank_app_hexagonal.domain.customer.exception.EmailAlreadyInUseException;
import com.hexagonal.bank_app_hexagonal.domain.customer.model.Customer;
import com.hexagonal.bank_app_hexagonal.domain.customer.port.CustomerPort;
import com.hexagonal.bank_app_hexagonal.domain.customer.usecase.CustomerRegister;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class CustomerRegisterUseCaseHandler implements UseCaseHandler<Customer, CustomerRegister> {

    private final CustomerPort customerPort;

    @Override
    public Customer handle(CustomerRegister useCase) {
        validateEmailUniquenessOrElseThrow(useCase.getEmail());
        return customerPort.create(useCase);
    }

    private void validateEmailUniquenessOrElseThrow(String email) {
        if(customerPort.isEmailAlreadyInUse(email)) {
            throw new EmailAlreadyInUseException(email);
        }
    }
}
