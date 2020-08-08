package com.mybank.demo.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class NewTransactionEvent {
    private final UUID accountId;
    private final BigDecimal amount;

    public NewTransactionEvent(UUID accountId, BigDecimal amount) {
        this.accountId = accountId;
        this.amount = amount;
    }
}
