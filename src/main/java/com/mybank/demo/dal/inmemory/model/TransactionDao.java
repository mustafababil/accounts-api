package com.mybank.demo.dal.inmemory.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
public class TransactionDao {
    @Id
    private UUID transactionId;

    private String amount;

    private Instant transactionTimestamp = Instant.now();

    private UUID accountId;
}
