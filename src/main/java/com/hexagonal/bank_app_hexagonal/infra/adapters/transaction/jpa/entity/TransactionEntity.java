package com.hexagonal.bank_app_hexagonal.infra.adapters.transaction.jpa.entity;


import com.hexagonal.bank_app_hexagonal.domain.common.model.TransactionType;
import com.hexagonal.bank_app_hexagonal.domain.transaction.model.Transaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "sender_account_id")
    private String senderAccountId;

    @Column(name = "receiver_account_id")
    private String receiverAccountId;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "amount")
    private BigDecimal amount;

    @CreationTimestamp
    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "description")
    private String description;

    public Transaction toModel(){
        return  Transaction.builder()
                .id(id)
                .senderAccountId(senderAccountId)
                .receiverAccountId(receiverAccountId)
                .transactionType(transactionType)
                .amount(amount)
                .date(date)
                .description(description)
                .build();
    }
}
