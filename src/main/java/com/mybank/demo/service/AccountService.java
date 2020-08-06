package com.mybank.demo.service;

import com.mybank.demo.model.Account;
import com.mybank.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createNewAccount(UUID customerId, BigDecimal initialCredit) {
        final Account newAccount = new Account();
        newAccount.setAccountId(UUID.randomUUID());
        newAccount.setCustomerId(customerId);
        newAccount.setBalance(initialCredit);

        if (initialCredit.compareTo(BigDecimal.ZERO) != 0) {
            // create transaction event
        }

        return accountRepository.save(newAccount);
    }
}
