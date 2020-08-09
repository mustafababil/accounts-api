package com.mybank.demo.controller;

import com.mybank.demo.controller.model.generated.NewAccountRequestObject;
import com.mybank.demo.model.Account;
import com.mybank.demo.model.Customer;
import com.mybank.demo.model.Transaction;
import com.mybank.demo.model.exception.AccountNotFoundException;
import com.mybank.demo.test.ControllerIntegrationTests;
import com.mybank.demo.service.AccountService;
import com.mybank.demo.service.CustomerService;
import com.mybank.demo.service.TransactionService;
import com.mybank.demo.test.SampleGenerator;
import com.mybank.demo.test.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

public class AccountsApiControllerIntegrationTests extends ControllerIntegrationTests {

    private static final String POST_NEW_ACCOUNT_ENDPOINT = "/api/v1/accounts";
    private static final String GET_ACCOUNT_DETAILS_ENDPOINT = "/api/v1/accounts/{accountId}";

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void whenPostValidNewAccountRequest_thenSuccessfulResponse() throws Exception {
        final NewAccountRequestObject newAccountRequest = SampleGenerator.createNewAccountRequestObject();
        final Account createdAccount = SampleGenerator.createAccount();

        given(accountService.createNewAccount(any(UUID.class), any(BigDecimal.class))).willReturn(createdAccount);

        mockMvc.perform(MockMvcRequestBuilders.post(POST_NEW_ACCOUNT_ENDPOINT)
                .content(TestUtils.asJsonString(newAccountRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.initialCredit").exists());
    }

    @Test
    public void whenPostNewAccountRequestWithInvalidCredit_thenErrorResponse() throws Exception {
        final NewAccountRequestObject newAccountRequest = SampleGenerator.createNewAccountRequestObject();
        newAccountRequest.setInitialCredit("invalid credit");

        mockMvc.perform(MockMvcRequestBuilders.post(POST_NEW_ACCOUNT_ENDPOINT)
                .content(TestUtils.asJsonString(newAccountRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors").isNotEmpty());
    }

    @Test
    public void whenGetAccountDetailsWithValidId_thenSuccessfulResponse() throws Exception {
        final int transactionCount = 5;
        final Account account = SampleGenerator.createAccount();
        final List<Transaction> transactions = SampleGenerator.createTransactions(transactionCount, account.getAccountId());
        final Customer customer = SampleGenerator.createCustomer();

        String accountId = account.getAccountId().toString();
        given(accountService.getAccountDetails(eq(account.getAccountId()))).willReturn(account);
        given(transactionService.getTransactions(eq(account.getAccountId()))).willReturn(transactions);
        given(customerService.getCustomer(eq(account.getAccountId()))).willReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get(GET_ACCOUNT_DETAILS_ENDPOINT, accountId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactions").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactions").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactions", hasSize(transactionCount)));
    }
    
}
