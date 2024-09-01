package com.hexagonal.bank_app_hexagonal.domain.customer.port;

import com.hexagonal.bank_app_hexagonal.domain.customer.model.Customer;
import com.hexagonal.bank_app_hexagonal.domain.customer.usecase.CustomerRegister;

public interface CustomerPort {

    Customer create(CustomerRegister customerRegister);

    boolean isEmailAlreadyInUse(String email);

    Customer retrieve(String email);

}
