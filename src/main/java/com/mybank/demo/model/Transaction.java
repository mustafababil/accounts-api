package com.mybank.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    @NotNull
    private UUID transactionId;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Instant transactionTimestamp;

    @NotNull
    private UUID accountId;
}
