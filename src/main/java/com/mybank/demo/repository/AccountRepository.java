package com.mybank.demo.repository;

import com.mybank.demo.model.Account;

public interface AccountRepository {
    Account save(Account account);
}
