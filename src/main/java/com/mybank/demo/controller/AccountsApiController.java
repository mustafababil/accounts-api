package com.mybank.demo.controller;

import com.mybank.demo.controller.generated.AccountsApi;
import com.mybank.demo.controller.mapper.AccountsApiMapperService;
import com.mybank.demo.controller.model.generated.NewAccountRequestObject;
import com.mybank.demo.controller.model.generated.NewAccountResponseObject;
import com.mybank.demo.model.Account;
import com.mybank.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller
public class AccountsApiController implements AccountsApi {

    private final AccountsApiMapperService mapper;
    private final AccountService accountService;

    @Autowired
    public AccountsApiController(AccountsApiMapperService accountsApiMapper, AccountService accountService) {
        this.mapper = accountsApiMapper;
        this.accountService = accountService;
    }

    @Override
    public ResponseEntity<NewAccountResponseObject> createNewAccount(NewAccountRequestObject newAccountRequestObject) {
        final BigDecimal initialCredit = new BigDecimal(newAccountRequestObject.getInitialCredit());
        // TODO Implement excp handler for NumberFormatException

        final Account newAccount = accountService.createNewAccount(newAccountRequestObject.getCustomerId(), initialCredit);
        final NewAccountResponseObject response = mapper.toNewAccountResponseObject(newAccount);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
