package com.mybank.demo.repository;

import com.mybank.demo.model.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Account save(Account account);
    Optional<Account> get(UUID accountId);
}
