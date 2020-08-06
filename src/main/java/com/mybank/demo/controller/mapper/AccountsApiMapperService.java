package com.mybank.demo.controller.mapper;

import com.mybank.demo.controller.model.generated.NewAccountResponseObject;
import com.mybank.demo.model.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountsApiMapperService {

    public NewAccountResponseObject toNewAccountResponseObject(Account newAccount) {
        final NewAccountResponseObject output = new NewAccountResponseObject();
        output.setAccountId(newAccount.getAccountId());
        output.setCustomerId(newAccount.getCustomerId());
        output.setInitialCredit(newAccount.getBalance().toString());
        return output;
    }
}
