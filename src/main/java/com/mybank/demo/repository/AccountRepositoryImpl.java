package com.mybank.demo.repository;

import com.mybank.demo.dal.inmemory.mapper.AccountMapper;
import com.mybank.demo.dal.inmemory.model.AccountDao;
import com.mybank.demo.dal.inmemory.repository.RelationalAccountRepository;
import com.mybank.demo.model.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private final RelationalAccountRepository repository;
    private final AccountMapper mapper;

    public AccountRepositoryImpl(RelationalAccountRepository relationalAccountRepository,
                                 AccountMapper accountMapper) {
        this.repository = relationalAccountRepository;
        this.mapper = accountMapper;
    }

    @Override
    public Account save(Account account) {
        AccountDao dao = mapper.toDao(account);
        AccountDao savedDao = repository.save(dao);

        return mapper.toModel(savedDao);
    }

    @Override
    public Optional<Account> get(UUID accountId) {
        Optional<AccountDao> optionalDao = repository.findById(accountId);
        return optionalDao.map(mapper::toModel);
    }
}
