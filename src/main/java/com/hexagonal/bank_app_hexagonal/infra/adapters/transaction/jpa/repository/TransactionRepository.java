package com.hexagonal.bank_app_hexagonal.infra.adapters.transaction.jpa.repository;

import com.hexagonal.bank_app_hexagonal.infra.adapters.transaction.jpa.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM transaction WHERE sender_account_id = ?1 AND transaction_type = 'INITIAL'")
    Optional<TransactionEntity> findInitialTransactionByAccountId(String accountId);

    @Query(nativeQuery = true, value = "SELECT * FROM transaction WHERE sender_account_id =?1 OR receiver_account_id = ?1")
    List<TransactionEntity> findAllByAccountId(String accountId);
}
