package com.mybank.demo.dal.inmemory.repository;

import com.mybank.demo.dal.inmemory.model.AccountDao;
import com.mybank.demo.test.DatabaseIntegrationTests;
import com.mybank.demo.test.SampleGenerator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RelationalAccountRepositoryDBIntegrationTests extends DatabaseIntegrationTests {

    @Autowired
    private RelationalAccountRepository repository;

    @Test
    public void testSave() {
        // Given
        AccountDao dao = SampleGenerator.createAccountDao();

        // When
        AccountDao output = repository.save(dao);

        // Then
        assertNotNull(output);
        assertThat(output).isEqualToComparingFieldByField(dao);
    }

    @Test
    public void testFindById() {
        // Given
        AccountDao dao = SampleGenerator.createAccountDao();
        AccountDao savedDao = repository.save(dao);

        // When
        Optional<AccountDao> output = repository.findById(savedDao.getAccountId());

        // Then
        assertNotNull(output);
        assertTrue(output.isPresent());
        assertThat(output.get()).isEqualToComparingFieldByField(savedDao);
    }
}
