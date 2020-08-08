package com.mybank.demo.service;

import com.mybank.demo.model.NewTransactionEvent;
import com.mybank.demo.model.Transaction;
import com.mybank.demo.repository.TransactionRepository;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Async
    @EventListener
    public Transaction createInitialCreditTransaction(NewTransactionEvent newTransactionEvent) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(newTransactionEvent.getAccountId());
        transaction.setAmount(newTransactionEvent.getAmount());
        transaction.setTransactionId(UUID.randomUUID());
        transaction.setTransactionTimestamp(Instant.now());

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactions(UUID accountId) {
        return transactionRepository.getTransactions(accountId);
    }
}
