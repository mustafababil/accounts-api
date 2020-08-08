package com.mybank.demo.dal.inmemory.repository;

import com.mybank.demo.dal.inmemory.model.TransactionDao;
import com.mybank.demo.test.DatabaseIntegrationTests;
import com.mybank.demo.test.SampleGenerator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

public class RelationalTransactionRepositoryDBIntegrationTests extends DatabaseIntegrationTests {

    @Autowired
    private RelationalTransactionRepository repository;

    @Test
    public void testSave() {
        // Given
        UUID accountId = UUID.randomUUID();
        TransactionDao dao = SampleGenerator.createTransactionDao(accountId);

        // When
        TransactionDao output = repository.save(dao);

        // Then
        assertNotNull(output);
        assertThat(output).isEqualToComparingFieldByField(dao);
    }

    @Test
    public void testFindByAccountId() {
        // Given
        UUID accountId = UUID.randomUUID();
        TransactionDao dao = SampleGenerator.createTransactionDao(accountId);
        TransactionDao savedTransaction = repository.save(dao);

        // When
        List<TransactionDao> accountTransactions = repository.findByAccountId(accountId);

        // Then
        assertNotNull(accountTransactions);
        assertThat(accountTransactions).hasSize(1);
        assertThat(accountTransactions.get(0)).isEqualToComparingFieldByField(savedTransaction);
    }
}
