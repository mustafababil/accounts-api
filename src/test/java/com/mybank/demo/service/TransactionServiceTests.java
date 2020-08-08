package com.mybank.demo.service;

import com.mybank.demo.model.NewTransactionEvent;
import com.mybank.demo.model.Transaction;
import com.mybank.demo.repository.TransactionRepository;
import com.mybank.demo.test.SampleGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TransactionServiceTests {

    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Before
    public void setUp() {
        transactionService = new TransactionService(transactionRepository);
    }

    @Test
    public void testCreateInitialCreditTransaction() {
        // Given
        NewTransactionEvent newTransactionEvent = SampleGenerator.createNewTransactionEvent();

        // When
        transactionService.createInitialCreditTransaction(newTransactionEvent);

        // Then
        verify(transactionRepository, times(1)).save(any(Transaction.class));
        verifyNoMoreInteractions(transactionRepository);
    }

    @Test
    public void testGetTransactions() {
        // Given
        UUID accountId = UUID.randomUUID();
        List<Transaction> transactions = SampleGenerator.createTransactions(5, accountId);
        when(transactionRepository.getTransactions(eq(accountId))).thenReturn(transactions);

        // When
        List<Transaction> result = transactionService.getTransactions(accountId);

        // THen
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertThat(result).hasSameSizeAs(transactions);
        verify(transactionRepository, times(1)).getTransactions(eq(accountId));
        verifyNoMoreInteractions(transactionRepository);
    }
}
