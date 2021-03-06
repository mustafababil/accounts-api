package com.mybank.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Account {
    @NotNull
    private UUID accountId;

    @NotNull
    private UUID customerId;

    @NotNull
    private BigDecimal balance;
}
