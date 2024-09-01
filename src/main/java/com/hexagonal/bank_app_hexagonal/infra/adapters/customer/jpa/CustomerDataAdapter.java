package com.hexagonal.bank_app_hexagonal.infra.adapters.customer.jpa;

import com.hexagonal.bank_app_hexagonal.domain.customer.exception.CustomerNotFoundException;
import com.hexagonal.bank_app_hexagonal.domain.customer.model.Customer;
import com.hexagonal.bank_app_hexagonal.domain.customer.port.CustomerPort;
import com.hexagonal.bank_app_hexagonal.domain.customer.usecase.CustomerRegister;
import com.hexagonal.bank_app_hexagonal.infra.adapters.customer.jpa.entity.CustomerEntity;
import com.hexagonal.bank_app_hexagonal.infra.adapters.customer.jpa.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerDataAdapter implements CustomerPort {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Customer create(CustomerRegister customerRegister) {
        CustomerEntity customerEntity = CustomerEntity.builder()
                .firstName(customerRegister.getFirstName())
                .lastName(customerRegister.getLastName())
                .email(customerRegister.getEmail())
                .password(passwordEncoder.encode(customerRegister.getPassword()))
                .phoneNumber(customerRegister.getPhoneNumber())
                .dateOfBirth(customerRegister.getDateOfBirth())
                .build();

        CustomerEntity savedCustomerEntity = customerRepository.save(customerEntity);

        return savedCustomerEntity.toModel();
    }

    @Override
    public boolean isEmailAlreadyInUse(String email) {
        Optional<CustomerEntity> customerEntity = customerRepository.findByEmail(email);
        return customerEntity.isPresent();
    }

    @Override
    public Customer retrieve(String email) {
        return customerRepository
                .findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(email))
                .toModel();
    }
}
