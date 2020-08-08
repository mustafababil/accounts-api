package com.mybank.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Customer {
    @NotNull
    private UUID customerId;

    @NotNull
    @Size(max = 100, message = "Name length can be 100 characters max")
    private String name;

    @NotNull
    @Size(max = 100, message = "Surname length can be 100 characters max")
    private String surname;
}
