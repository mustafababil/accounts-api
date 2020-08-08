package com.mybank.demo.service;

import com.mybank.demo.model.Account;
import com.mybank.demo.model.NewTransactionEvent;
import com.mybank.demo.model.exception.AccountNotFoundException;
import com.mybank.demo.repository.AccountRepository;
import com.mybank.demo.test.SampleGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AccountServiceTests {

    private AccountService accountService;

    @Mock
    ApplicationEventPublisher applicationEventPublisher;

    @Captor
    protected ArgumentCaptor<Object> publishEventCaptor;

    @Mock
    private AccountRepository accountRepository;

    @Before
    public void setUp() {
        accountService = new AccountService(applicationEventPublisher, accountRepository);
    }

    @Test
    public void testCreateNewAccountWithNonZeroInitialCredit() {
        // Given
        final UUID customerId = UUID.randomUUID();
        final BigDecimal initialCredit = BigDecimal.TEN;

        final Account account = SampleGenerator.createAccount();
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        // When
        final Account createdAccount = accountService.createNewAccount(customerId, initialCredit);

        // Then
        assertNotNull(createdAccount);
        assertThat(createdAccount).isSameAs(account);
        verify(accountRepository, times(1)).save(any(Account.class));
        verify(applicationEventPublisher, times(1)).publishEvent(publishEventCaptor.capture());
        assertThat(publishEventCaptor.getValue()).isInstanceOf(NewTransactionEvent.class);
        verifyNoMoreInteractions(accountRepository);
        verifyNoMoreInteractions(applicationEventPublisher);
    }

    @Test
    public void testCreateNewAccountWithZeroInitialCredit() {
        // Given
        final UUID customerId = UUID.randomUUID();
        final BigDecimal initialCredit = BigDecimal.ZERO; // No initial credit

        final Account account = SampleGenerator.createAccount();
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        // When
        final Account createdAccount = accountService.createNewAccount(customerId, initialCredit);

        // Then
        assertNotNull(createdAccount);
        assertThat(createdAccount).isSameAs(account);
        verify(accountRepository, times(1)).save(any(Account.class));
        verifyNoMoreInteractions(accountRepository);
        verifyNoInteractions(applicationEventPublisher);
    }

    @Test
    public void testGetAccountDetailsHappyPath() {
        // Given
        final UUID accountId = UUID.randomUUID();

        final Account account = SampleGenerator.createAccount();
        when(accountRepository.get(eq(accountId))).thenReturn(Optional.of(account));

        // When
        final Account foundAccount = accountService.getAccountDetails(accountId);

        // Then
        assertNotNull(foundAccount);
        assertThat(foundAccount).isSameAs(account);
        verify(accountRepository, times(1)).get(eq(accountId));
        verifyNoMoreInteractions(accountRepository);
    }

    @Test(expected = AccountNotFoundException.class)
    public void testGetAccountDetailsUnhappyPath() {
        // Given
        final UUID accountId = UUID.randomUUID();

        when(accountRepository.get(eq(accountId))).thenReturn(Optional.empty());

        // When
        final Account account = accountService.getAccountDetails(accountId);

        // Then
        // exception
    }

}
