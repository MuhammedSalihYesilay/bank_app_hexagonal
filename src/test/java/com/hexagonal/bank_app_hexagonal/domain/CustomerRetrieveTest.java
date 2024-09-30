package com.hexagonal.bank_app_hexagonal.domain;

import com.hexagonal.bank_app_hexagonal.domain.adapters.CustomerFakeDataAdapter;
import com.hexagonal.bank_app_hexagonal.domain.customer.CustomerRetrieveUseCaseHandler;
import com.hexagonal.bank_app_hexagonal.domain.customer.usecase.CustomerRetrieve;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CustomerRetrieveTest {

    CustomerRetrieveUseCaseHandler customerRetrieveUseCaseHandler;

    @BeforeEach
    void setUp() {
        customerRetrieveUseCaseHandler = new CustomerRetrieveUseCaseHandler(new CustomerFakeDataAdapter());
    }

    @Test
    void should_retrieve_customer() {
        // given
        CustomerRetrieve customerRetrieve = CustomerRetrieve.builder()
                .email("test email")
                .build();

        // when
        var costumer = customerRetrieveUseCaseHandler.handle(customerRetrieve);

        // than
        assertThat(costumer).isNotNull();
    }
}
