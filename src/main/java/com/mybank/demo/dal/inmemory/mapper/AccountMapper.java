package com.mybank.demo.dal.inmemory.mapper;

import com.mybank.demo.dal.inmemory.model.AccountDao;
import com.mybank.demo.model.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountMapper {
    public AccountDao toDao(Account account) {
        AccountDao dao = new AccountDao();
        dao.setAccountId(account.getAccountId());
        dao.setBalance(account.getBalance().toString());
        dao.setCustomerId(account.getCustomerId());

        return dao;
    }

    public Account toModel(AccountDao savedDao) {
        Account model = new Account();
        model.setAccountId(savedDao.getAccountId());
        model.setBalance(new BigDecimal(savedDao.getBalance()));
        model.setCustomerId(savedDao.getCustomerId());
        return model;
    }
}
