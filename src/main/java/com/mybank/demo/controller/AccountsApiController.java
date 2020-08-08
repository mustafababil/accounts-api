package com.mybank.demo.controller;

import com.mybank.demo.controller.generated.AccountsApi;
import com.mybank.demo.controller.mapper.AccountsApiMapperService;
import com.mybank.demo.controller.model.generated.AccountDetailsResponseObject;
import com.mybank.demo.controller.model.generated.NewAccountRequestObject;
import com.mybank.demo.controller.model.generated.NewAccountResponseObject;
import com.mybank.demo.model.Account;
import com.mybank.demo.model.Customer;
import com.mybank.demo.model.Transaction;
import com.mybank.demo.model.exception.InvalidCurrencyFormatException;
import com.mybank.demo.service.AccountService;
import com.mybank.demo.service.CustomerService;
import com.mybank.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
public class AccountsApiController implements AccountsApi {

    private final AccountsApiMapperService mapper;
    private final AccountService accountService;
    private final TransactionService transactionService;
    private final CustomerService customerService;

    @Autowired
    public AccountsApiController(AccountsApiMapperService accountsApiMapper, AccountService accountService,
                                TransactionService transactionService, CustomerService customerService) {
        this.mapper = accountsApiMapper;
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<NewAccountResponseObject> createNewAccount(NewAccountRequestObject newAccountRequestObject) {
        final BigDecimal initialCredit;
        try {
            initialCredit = new BigDecimal(newAccountRequestObject.getInitialCredit());
        } catch (NumberFormatException nfe) {
            String message = String.format("Given initial credit '%s' is in invalid format.", newAccountRequestObject.getInitialCredit());
            throw new InvalidCurrencyFormatException(message);
        }

        final Account newAccount = accountService.createNewAccount(newAccountRequestObject.getCustomerId(), initialCredit);
        final NewAccountResponseObject response = mapper.toNewAccountResponseObject(newAccount);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AccountDetailsResponseObject> getAccountDetails(UUID id) {
        final Account account = accountService.getAccountDetails(id);
        final List<Transaction> transactions = transactionService.getTransactions(id);
        final Customer customer = customerService.getCustomer(id);

        final AccountDetailsResponseObject response = mapper.toAccountDetailsResponseObject(account, transactions, customer);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
