package com.mybank.demo.repository;

import com.mybank.demo.model.Transaction;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
}
