package com.mybank.demo.service;

import com.mybank.demo.model.Account;
import com.mybank.demo.model.NewTransactionEvent;
import com.mybank.demo.model.exception.AccountNotFoundException;
import com.mybank.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    private ApplicationEventPublisher applicationEventPublisher;
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(ApplicationEventPublisher applicationEventPublisher, AccountRepository accountRepository) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.accountRepository = accountRepository;
    }

    public Account createNewAccount(UUID customerId, BigDecimal initialCredit) {
        final Account newAccount = new Account();
        newAccount.setAccountId(UUID.randomUUID());
        newAccount.setCustomerId(customerId);
        newAccount.setBalance(initialCredit);

        Account createdAccount = accountRepository.save(newAccount);

        // Create a transaction record if initial credit is not zero
        if (initialCredit.compareTo(BigDecimal.ZERO) != 0) {
            applicationEventPublisher.publishEvent(new NewTransactionEvent(createdAccount.getAccountId(), initialCredit));
        }

        return createdAccount;
    }

    public Account getAccountDetails(UUID accountId) {
        Optional<Account> optionalAccount = accountRepository.get(accountId);

        if (optionalAccount.isEmpty()) {
            String message = String.format("Account with Id '%s' not found!", accountId.toString());
            throw new AccountNotFoundException(message);
        }

        return optionalAccount.get();
    }
}
