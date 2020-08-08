package com.mybank.demo.repository;

import com.mybank.demo.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    List<Transaction> getTransactions(UUID accountId);
}
