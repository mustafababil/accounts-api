package com.mybank.demo.dal.inmemory.repository;

import com.mybank.demo.dal.inmemory.model.AccountDao;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RelationalAccountRepository extends CrudRepository<AccountDao, UUID> {
}
