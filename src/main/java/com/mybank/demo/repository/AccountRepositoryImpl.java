package com.mybank.demo.repository;

import com.mybank.demo.model.Account;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    /*
     * This is a primitive in-memory persistence repository
     */
    private final Map<UUID, Account> vault;

    public AccountRepositoryImpl() {
        vault = new ConcurrentHashMap<>();
    }

    @Override
    public Account save(Account account) {
        vault.put(account.getAccountId(), account);
        return account;
    }
}
