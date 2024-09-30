package com.hexagonal.bank_app_hexagonal.domain;

import com.hexagonal.bank_app_hexagonal.domain.adapters.CustomerFakeDataAdapter;
import com.hexagonal.bank_app_hexagonal.domain.customer.CustomerRegisterUseCaseHandler;
import com.hexagonal.bank_app_hexagonal.domain.customer.exception.EmailAlreadyInUseException;
import com.hexagonal.bank_app_hexagonal.domain.customer.model.Customer;
import com.hexagonal.bank_app_hexagonal.domain.customer.usecase.CustomerRegister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerRegisterTest {

    CustomerRegisterUseCaseHandler customerRegisterUseCaseHandler;

    @BeforeEach
    void setUp() {
        customerRegisterUseCaseHandler = new CustomerRegisterUseCaseHandler(new CustomerFakeDataAdapter());
    }

    @Test
    void should_register_customer() {
        //given
        CustomerRegister customerRegister = CustomerRegister.builder()
                .email("test email")
                .firstName("test first name")
                .lastName("test last name")
                .phoneNumber("0551 678 76 90")
                .dateOfBirth(LocalDateTime.of(2021, 1, 1, 20, 0, 0))
                .build();

        //when
        var customer = customerRegisterUseCaseHandler.handle(customerRegister);

        //than
        assertThat(customer).isNotNull()
                .returns("id", Customer::getId)
                .returns("test email", Customer::getEmail)
                .returns("test first name", Customer::getFirstName)
                .returns("test last name", Customer::getLastName)
                .returns("0551 678 76 90", Customer::getPhoneNumber)
                .returns(LocalDateTime.of(2021, 1, 1, 20, 0, 0), Customer::getDateOfBirth);
    }

    @Test
    void should_throw_exception_when_email_is_already_in_use() {
        // given
        CustomerRegister customerRegister = CustomerRegister.builder()
                .email("test email")
                .firstName("test first name")
                .lastName("test last name")
                .phoneNumber("0551 678 76 90")
                .dateOfBirth(LocalDateTime.of(2021, 1, 1, 20, 0, 0))
                .password("password123")
                .build();

        CustomerFakeDataAdapter fakeDataAdapter = new CustomerFakeDataAdapter() {
            @Override
            public boolean isEmailAlreadyInUse(String email) {
                return true;
            }
        };

        CustomerRegisterUseCaseHandler useCaseHandler = new CustomerRegisterUseCaseHandler(fakeDataAdapter);

        // when + then
        assertThrows(EmailAlreadyInUseException.class, () -> useCaseHandler.handle(customerRegister));
    }
}
