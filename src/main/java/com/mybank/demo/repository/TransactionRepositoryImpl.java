package com.mybank.demo.repository;

import com.mybank.demo.dal.inmemory.mapper.TransactionMapper;
import com.mybank.demo.dal.inmemory.model.TransactionDao;
import com.mybank.demo.dal.inmemory.repository.RelationalTransactionRepository;
import com.mybank.demo.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    private final RelationalTransactionRepository repository;
    private final TransactionMapper mapper;

    public TransactionRepositoryImpl(RelationalTransactionRepository relationalTransactionRepository,
                                     TransactionMapper transactionMapper) {
        this.repository = relationalTransactionRepository;
        this.mapper = transactionMapper;
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionDao dao = mapper.toDao(transaction);
        TransactionDao createdTransaction = repository.save(dao);

        return mapper.toModel(createdTransaction);
    }

    @Override
    public List<Transaction> getTransactions(UUID accountId) {
        List<TransactionDao> daos = repository.findByAccountId(accountId);

        return daos.stream().map(mapper::toModel).collect(Collectors.toList());
    }
}
