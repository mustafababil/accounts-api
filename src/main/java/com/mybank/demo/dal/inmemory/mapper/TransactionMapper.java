package com.mybank.demo.dal.inmemory.mapper;

import com.mybank.demo.dal.inmemory.model.TransactionDao;
import com.mybank.demo.model.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class TransactionMapper {

    public TransactionDao toDao(Transaction transaction) {
        TransactionDao dao = new TransactionDao();
        dao.setAccountId(transaction.getAccountId());
        dao.setTransactionId(transaction.getTransactionId());
        dao.setAmount(transaction.getAmount().toString());
        dao.setTransactionTimestamp(Instant.now());

        return dao;
    }

    public Transaction toModel(TransactionDao dao) {
        Transaction model = new Transaction();
        model.setAccountId(dao.getAccountId());
        model.setTransactionId(dao.getTransactionId());
        model.setAmount(new BigDecimal(dao.getAmount()));
        model.setTransactionTimestamp(dao.getTransactionTimestamp());

        return model;
    }

}
