package com.sha.serverbikemanagement.service;

import com.sha.serverbikemanagement.model.Transaction;
import com.sha.serverbikemanagement.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl  implements TransactionService{


    private TransactionRepository transactionRepository;

    @Override
    public Transaction saveTransaction(final Transaction transaction){
        return transactionRepository.save(transaction);
    }

    @Override
    public Long numberOfTransactions(){
        return transactionRepository.count();
    }

    @Override
    public List<Transaction> findAllTransactions(){
        return transactionRepository.findAll();
    }
}
