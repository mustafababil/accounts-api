package com.mybank.demo.service;

import com.mybank.demo.model.Customer;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Service
public class CustomerService {

    public Customer getCustomer(@NotNull UUID accountId) {
        Customer randomCustomer = new Customer();
        randomCustomer.setCustomerId(UUID.randomUUID());
        randomCustomer.setName("John");
        randomCustomer.setSurname("Doe");
        return randomCustomer;
    }
}
