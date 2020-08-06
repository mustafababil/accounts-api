package com.mybank.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Account {
    private UUID accountId;
    private UUID customerId;
    private BigDecimal balance;
}
