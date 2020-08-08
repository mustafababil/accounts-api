package com.mybank.demo.controller.mapper;

import com.mybank.demo.controller.model.generated.AccountDetailsResponseObject;
import com.mybank.demo.controller.model.generated.NewAccountResponseObject;
import com.mybank.demo.controller.model.generated.TransactionResponseObject;
import com.mybank.demo.model.Account;
import com.mybank.demo.model.Customer;
import com.mybank.demo.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountsApiMapperService {

    public NewAccountResponseObject toNewAccountResponseObject(Account newAccount) {
        final NewAccountResponseObject output = new NewAccountResponseObject();
        output.setAccountId(newAccount.getAccountId());
        output.setCustomerId(newAccount.getCustomerId());
        output.setInitialCredit(newAccount.getBalance().toString());
        return output;
    }

    public AccountDetailsResponseObject toAccountDetailsResponseObject(Account account, List<Transaction> transactions,
                                                                       Customer customer) {
        final AccountDetailsResponseObject output = new AccountDetailsResponseObject();
        output.setAccountId(account.getAccountId());
        output.setBalance(account.getBalance().toString());
        output.setCustomerId(account.getCustomerId());
        output.setName(customer.getName());
        output.setSurname(customer.getSurname());

        List<TransactionResponseObject> transactionResponseObjects = transactions.stream()
                .map(this::toTransactionResponseObject).collect(Collectors.toList());
        output.setTransactions(transactionResponseObjects);
        return output;
    }

    public TransactionResponseObject toTransactionResponseObject(Transaction transaction) {
        TransactionResponseObject output = new TransactionResponseObject();
        output.setTransactionId(transaction.getTransactionId());
        output.setAmount(transaction.getAmount().toString());
        output.setTimestamp(transaction.getTransactionTimestamp());
        return output;
    }
}
