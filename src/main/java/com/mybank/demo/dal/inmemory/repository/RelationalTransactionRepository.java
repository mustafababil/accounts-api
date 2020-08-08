package com.mybank.demo.dal.inmemory.repository;

import com.mybank.demo.dal.inmemory.model.TransactionDao;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RelationalTransactionRepository extends CrudRepository<TransactionDao, UUID> {
}
