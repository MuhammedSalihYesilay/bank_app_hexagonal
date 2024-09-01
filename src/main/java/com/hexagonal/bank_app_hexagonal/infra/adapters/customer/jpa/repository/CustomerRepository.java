package com.hexagonal.bank_app_hexagonal.infra.adapters.customer.jpa.repository;

import com.hexagonal.bank_app_hexagonal.infra.adapters.customer.jpa.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

    Optional<CustomerEntity> findByEmail(String email);
}
