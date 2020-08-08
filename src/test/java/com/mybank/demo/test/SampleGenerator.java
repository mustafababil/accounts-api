package com.mybank.demo.test;

import com.mybank.demo.controller.model.generated.NewAccountRequestObject;
import com.mybank.demo.dal.inmemory.model.AccountDao;
import com.mybank.demo.dal.inmemory.model.TransactionDao;
import com.mybank.demo.model.Account;
import com.mybank.demo.model.Customer;
import com.mybank.demo.model.NewTransactionEvent;
import com.mybank.demo.model.Transaction;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class SampleGenerator {

    private SampleGenerator() {
    }

    public static NewAccountRequestObject createNewAccountRequestObject() {
        final NewAccountRequestObject sample = new NewAccountRequestObject();
        sample.setCustomerId(UUID.randomUUID());
        sample.setInitialCredit("123.45");
        return sample;
    }

    public static Account createAccount() {
        final Account sample = new Account();
        sample.setAccountId(UUID.randomUUID());
        sample.setCustomerId(UUID.randomUUID());
        sample.setBalance(BigDecimal.TEN);
        return sample;
    }

    public static List<Transaction> createTransactions(int transactionCount, UUID accountId) {
        final List<Transaction> transactions = new ArrayList<>();
        for (int i=0; i<transactionCount; i++) {
            transactions.add(createTransaction(accountId));
        }
        return transactions;
    }

    private static Transaction createTransaction(UUID accountId) {
        final Transaction sample = new Transaction();
        sample.setAccountId(accountId);
        sample.setTransactionId(UUID.randomUUID());
        sample.setAmount(BigDecimal.ONE);
        sample.setTransactionTimestamp(Instant.now());
        return sample;
    }

    public static Customer createCustomer() {
        final Customer sample = new Customer();
        sample.setCustomerId(UUID.randomUUID());
        sample.setName("John");
        sample.setSurname("Doe");
        return sample;
    }

    public static NewTransactionEvent createNewTransactionEvent() {
        return new NewTransactionEvent(UUID.randomUUID(), BigDecimal.TEN);
    }

    public static AccountDao createAccountDao() {
        AccountDao sample = new AccountDao();
        sample.setAccountId(UUID.randomUUID());
        sample.setCustomerId(UUID.randomUUID());
        sample.setBalance(BigDecimal.TEN.toString());
        return sample;
    }

    public static TransactionDao createTransactionDao(UUID accountId) {
        TransactionDao sample = new TransactionDao();
        sample.setAccountId(accountId);
        sample.setTransactionId(UUID.randomUUID());
        sample.setAmount(BigDecimal.TEN.toString());
        sample.setTransactionTimestamp(Instant.now());
        return sample;
    }
}
